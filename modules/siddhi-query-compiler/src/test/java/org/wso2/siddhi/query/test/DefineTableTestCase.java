/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.siddhi.query.test;

import org.junit.Assert;
import org.junit.Test;
import org.wso2.siddhi.query.api.annotation.Annotation;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.definition.TableDefinition;
import org.wso2.siddhi.query.api.definition.io.Store;
import org.wso2.siddhi.query.compiler.SiddhiCompiler;
import org.wso2.siddhi.query.compiler.exception.SiddhiParserException;

public class DefineTableTestCase {


    @Test
    public void Test1() throws SiddhiParserException {
        TableDefinition streamDefinition = SiddhiCompiler.parseTableDefinition("define table cseStream ( symbol string, price int, volume float )");
        Assert.assertEquals(TableDefinition.
                        id("cseStream").
                        attribute("symbol", Attribute.Type.STRING).
                        attribute("price", Attribute.Type.INT).
                        attribute("volume", Attribute.Type.FLOAT).toString(),
                streamDefinition.toString());
    }

    @Test
    public void Test2() throws SiddhiParserException {
        TableDefinition streamDefinition = SiddhiCompiler.parseTableDefinition("define table `define` ( `string` string, price int, volume float );");
        Assert.assertEquals(TableDefinition.
                        id("define").
                        attribute("string", Attribute.Type.STRING).
                        attribute("price", Attribute.Type.INT).
                        attribute("volume", Attribute.Type.FLOAT).toString(),
                streamDefinition.toString());
    }

    @Test
    public void Test3() throws SiddhiParserException {
        TableDefinition streamDefinition = SiddhiCompiler.parseTableDefinition("define table cseStream ( symbol string, price int, volume float )");
        Assert.assertEquals(TableDefinition.
                        id("cseStream").
                        attribute("symbol", Attribute.Type.STRING).
                        attribute("price", Attribute.Type.INT).
                        attribute("volume", Attribute.Type.FLOAT).toString(),
                streamDefinition.toString());
    }

    @Test
    public void Test4() throws SiddhiParserException {
        TableDefinition streamDefinition = SiddhiCompiler.parseTableDefinition("" +
                " @from(datasource='MyDatabase','CUSTOM')" +
                " define table cseStream ( symbol string, price int, volume float )");
        Assert.assertEquals(TableDefinition.
                        id("cseStream").
                        attribute("symbol", Attribute.Type.STRING).
                        attribute("price", Attribute.Type.INT).
                        attribute("volume", Attribute.Type.FLOAT).annotation(Annotation.create("from").element("datasource", "MyDatabase").element("CUSTOM")).toString(),
                streamDefinition.toString());
    }

    @Test
    public void Test5() throws SiddhiParserException {
        TableDefinition tableDefinition = SiddhiCompiler.parseTableDefinition("" +
                "define table FooTable (time long, data string) " +
                "store rdbms options (url \"http://localhost:8900\", " +
                "username \"test\");");
        Store store = Store.store("rdbms").
                option("url", "http://localhost:8900").
                option("username", "test");
        Assert.assertEquals(TableDefinition.id("FooTable").
                attribute("time", Attribute.Type.LONG).
                attribute("data", Attribute.Type.STRING).store(store).toString(),
                tableDefinition.toString());
    }
}
