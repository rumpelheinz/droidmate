// Copyright (c) 2012-2016 Saarland University
// All rights reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org

package org.droidmate.common

public class TextUtilsCategory
{

  public static wrapWith(String self, String brackets)
  {
    assert self != null
    assert brackets?.size() == 2
    return self.replaceFirst("^", brackets[0]).replaceFirst("\$", brackets[1])
  }
}
