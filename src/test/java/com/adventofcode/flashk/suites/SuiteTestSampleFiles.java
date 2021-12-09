package com.adventofcode.flashk.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import com.adventofcode.flashk.common.test.utils.TagConstant;

@Suite
@IncludeTags(TagConstant.SAMPLE)
@SelectPackages({"com.adventofcode.flashk"})
@SuiteDisplayName("Test puzzle sample files")
public class SuiteTestSampleFiles {

}
