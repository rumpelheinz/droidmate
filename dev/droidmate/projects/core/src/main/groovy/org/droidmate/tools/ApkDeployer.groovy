// Copyright (c) 2013-2015 Saarland University
// All right reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org

package org.droidmate.tools

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.droidmate.android_sdk.IApk
import org.droidmate.common.Assert
import org.droidmate.configuration.Configuration
import org.droidmate.device.IDeployableAndroidDevice
import org.droidmate.exceptions.DeviceException

/**
 * @see IApkDeployer#withDeployedApk(IDeployableAndroidDevice, Apk, groovy.lang.Closure)
 */
@Slf4j

@TypeChecked
public class ApkDeployer implements IApkDeployer
{

  private final Configuration cfg


  ApkDeployer(Configuration cfg)
  {
    this.cfg = cfg
  }

  /**
   * <p>
   * Deploys the {@code apk} on a {@code device} A(V)D, executes the {@code closure} and undeploys the apk from
   * the {@code device}
   * </p>
   */
  @Override
  public void withDeployedApk(IDeployableAndroidDevice device, IApk apk, Closure computation) throws DeviceException
  {
    log.debug("withDeployedApk(device, $apk.fileName, computation)")

    assert device != null
    Assert.checkClosureFirstParameterSignature(computation, IApk)

    // Deployment of apk on device will read some information from logcat, so it has to be cleared to ensure the
    // anticipated commands are not matched against logcat messages from previous deployments.
    device.clearLogcat()

    tryReinstallApk(device, apk)

    Throwable savedTryThrowable = null
    try
    {
      computation(apk)
    } catch (Throwable tryThrowable)
    {
      log.debug("! Caught ${tryThrowable.class.simpleName} in withDeployedApk.computation(apk). Rethrowing.")
      savedTryThrowable = tryThrowable
      throw savedTryThrowable

    } finally
    {
      log.debug("Finalizing: withDeployedApk.finally {} for computation(apk)")
      try
      {
        tryUndeployApk(device, apk)

      } catch (Throwable tearDownThrowable)
      {
        log.debug("! Caught ${tearDownThrowable.class.simpleName} in tryTearDown(apk). Adding suppressed exception, if any, and rethrowing.")
        if (savedTryThrowable != null)
          tearDownThrowable.addSuppressed(savedTryThrowable)
        throw tearDownThrowable
      }
      log.debug("Finalizing DONE: withDeployedApk.finally {} for computation(apk)")
    }

    log.trace("Undeployed apk {}", apk.fileName)
  }

  private void tryUndeployApk(IDeployableAndroidDevice device, IApk apk) throws DeviceException
  {
    device.clearLogcat() // Do so, so the logcat messages sent from the uninstalled apk won't interfere with the next one.

    if (cfg.uninstallApk)
    {
      log.info("Uninstalling $apk.fileName")
      device.clearPackage(apk.packageName)
      device.uninstallApk(apk.packageName, /* warnAboutFailure = */ true)
    } else
    {
      // If the apk is not uninstalled, some of its monitored services might remain, interfering with monitored
      // logcat messages expectations for next explored apk, making DroidMate throw an assertion error.
    }
  }

  private void tryReinstallApk(IDeployableAndroidDevice device, IApk apk) throws DeviceException
  {
    log.info("Reinstalling {}", apk.fileName)
    /* The apk is uninstalled before installation to ensure:
     - any cache will be purged.
     - a different version of the same app can be installed, if necessary (without uninstall, an error will be issued about
     certificates not matching or something like that)
    */
    device.uninstallApk(apk.packageName, /* warnAboutFailure  = */ false)
    device.installApk(apk)
  }

}