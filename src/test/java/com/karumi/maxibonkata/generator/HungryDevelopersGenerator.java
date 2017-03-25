/*
 * Copyright (C) 2016 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.maxibonkata.generator;

import com.karumi.maxibonkata.Developer;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import org.apache.commons.lang3.RandomStringUtils;

public class HungryDevelopersGenerator extends Generator<Developer> {

    public HungryDevelopersGenerator() {
        super(Developer.class);
    }

    @Override
    public Developer generate(SourceOfRandomness random, GenerationStatus status) {
        String name = RandomStringUtils.randomAlphabetic(random.nextInt(16));
        int numberOfMaxibons = random.nextInt(8, 10);

        return new Developer(name, numberOfMaxibons);
    }
}
