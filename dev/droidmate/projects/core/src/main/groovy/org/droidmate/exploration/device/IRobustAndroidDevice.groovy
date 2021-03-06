// Copyright (c) 2012-2016 Saarland University
// All rights reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org
package org.droidmate.exploration.device

import org.droidmate.android_sdk.IApk
import org.droidmate.device.IAndroidDevice
import org.droidmate.device.datatypes.IDeviceGuiSnapshot
import org.droidmate.exceptions.DeviceException

public interface IRobustDevice extends IAndroidDevice, IDeviceMessagesReader
{
  IDeviceGuiSnapshot ensureHomeScreenIsDisplayed() throws DeviceException

  Boolean appIsNotRunning(IApk apk) throws DeviceException

  void launchApp(IApk apk) throws DeviceException
}
