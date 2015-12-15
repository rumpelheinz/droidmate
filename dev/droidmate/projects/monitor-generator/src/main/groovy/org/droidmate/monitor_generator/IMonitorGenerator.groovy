// Copyright (c) 2013-2015 Saarland University
// All right reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org

package org.droidmate.monitor_generator

import org.droidmate.apis.ApiMethodSignature

interface IMonitorGenerator
{
 String generate(List<ApiMethodSignature> signatures)
}