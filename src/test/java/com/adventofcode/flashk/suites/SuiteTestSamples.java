package com.adventofcode.flashk.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import com.adventofcode.flashk.common.TagConstant;

@Suite
@IncludeTags(TagConstant.SAMPLE)
@SelectPackages({"com.adventofcode.flashk"})
public class SuiteTestSamples {

}
