package com.adventofcode.flashk.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import com.adventofcode.flashk.common.TagConstant;

@Suite
@IncludeTags(TagConstant.INPUT)
@SelectPackages({"com.adventofcode.flashk"})
@SuiteDisplayName("Test puzzle input files")
public class SuiteTestInputFiles {

}
