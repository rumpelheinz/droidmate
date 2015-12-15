// Copyright (c) 2013-2015 Saarland University
// All right reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org
package org.droidmate.exceptions

class UnsupportedMultimethodDispatch extends UnexpectedIfElseFallthroughError
{

  UnsupportedMultimethodDispatch(def param)
  {
    super("Unsupported multimethod dispatch for param of class: ${param.class}")
  }


}