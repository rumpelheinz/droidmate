// Copyright (c) 2012-2016 Saarland University
// All rights reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org
package org.droidmate.device

import org.droidmate.common_android.DeviceCommand
import org.droidmate.common_android.DeviceResponse
import org.droidmate.exceptions.DeviceException
import org.droidmate.exceptions.DeviceNeedsRebootException
import org.droidmate.exceptions.TcpServerUnreachableException

interface IUiautomatorDaemonClient
{
  DeviceResponse sendCommandToUiautomatorDaemon(DeviceCommand deviceCommand) throws DeviceNeedsRebootException, TcpServerUnreachableException, DeviceException

  void forwardPort() throws DeviceException

  void startUiaDaemon() throws DeviceException

  void waitForUiaDaemonToClose() throws DeviceException

  boolean getUiaDaemonThreadIsAlive()
}