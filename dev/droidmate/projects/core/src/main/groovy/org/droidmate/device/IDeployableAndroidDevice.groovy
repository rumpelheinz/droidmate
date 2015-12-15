// Copyright (c) 2013-2015 Saarland University
// All right reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org

package org.droidmate.device

import org.droidmate.android_sdk.IApk
import org.droidmate.exceptions.DeviceException

public interface IDeployableAndroidDevice
{

  void forwardPort(int port) throws DeviceException

  void reverseForwardPort(int port) throws DeviceException

  void pushJar(File jar) throws DeviceException

  void removeJar(File jar) throws DeviceException

  void installApk(IApk apk) throws DeviceException

  void uninstallApk(String apkPackageName, boolean warnAboutFailure) throws DeviceException

  Boolean clearPackage(String apkPackageName) throws DeviceException

  void clearLogcat() throws DeviceException

  void startUiaDaemon() throws DeviceException

  void stopUiaDaemon() throws DeviceException
}