/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.aurora.engine;

import org.junit.Assert;
import org.junit.Test;
import org.thymeleaf.aurora.text.ITextRepository;
import org.thymeleaf.aurora.text.TextRepositories;


public final class TextTest {



    @Test
    public void test() {

        final ITextRepository textRepository = TextRepositories.createLimitedSizeCacheRepository();

        final char[] buf1 = "hello".toCharArray();

        final Text c1 = new Text(textRepository);
        c1.setText(buf1, 0, 5, 10, 3);
        Assert.assertEquals("hello", extractText(c1));
        final String c1all = c1.getText();
        Assert.assertEquals("hello", c1all);
        Assert.assertSame(c1all, c1.getText());
        Assert.assertEquals(10, c1.getLine());
        Assert.assertEquals(3, c1.getCol());

        final String c1c0 = " something\nhere ";
        c1.setText(c1c0);
        Assert.assertSame(c1c0, c1.getText());
        Assert.assertSame(textRepository.getText(" something\nhere "), c1.getText());
        Assert.assertEquals(-1, c1.getLine());
        Assert.assertEquals(-1, c1.getCol());

        final String c1c2 = "hey!";
        c1.setText(c1c2);
        final String c1c2_2 = c1.getText();
        Assert.assertSame(c1c2, c1c2_2);
        Assert.assertSame(c1c2, c1.getText());

        c1.setText(c1c0.toCharArray(), 0, c1c0.length(), 11, 4);
        final String c1c3_2 = c1.getText();
        Assert.assertEquals(c1c0, c1c3_2);
        Assert.assertSame(c1c3_2, c1.getText());
        Assert.assertEquals(11, c1.getLine());
        Assert.assertEquals(4, c1.getCol());


        final String c2c1 = "hello";
        final Text c2 = new Text(textRepository, c2c1);
        final String c2cs1_2 = c2.getText();
        Assert.assertEquals(c2c1, c2cs1_2);
        Assert.assertSame(c2cs1_2, c2.getText());
        Assert.assertEquals(-1, c2.getLine());
        Assert.assertEquals(-1, c2.getCol());

    }




    private static String extractText(final Text text) {

        final StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            strBuilder.append(text.charAt(i));
        }
        return strBuilder.toString();

    }



    
}
