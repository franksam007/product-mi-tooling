/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    private TestUtils(){

    }

    /**
     * Get the mi build path to run mi commands.
     */
    public static  String getMIBuildPath() throws IOException {
        File miBuildFilePath = new File(".." + File.separator + ".." + File.separator + ".." + File.separator
                                        + "cmd" + File.separator + "build" + File.separator + "wso2mi-cli-" + System.getProperty("version")
                                        + File.separator + "bin" + File.separator + "mi");
        return miBuildFilePath.getCanonicalPath();
    }

    public static List<String> getOutputForCLICommand(String artifactType, String command) throws IOException {

        String[] arguments = new String[]{getMIBuildPath(),artifactType, command};
        return runCommandWithArgs(arguments);
    }

    public static List<String> getOutputForCLICommandArtifactName(String artifactType, String command, String artifactName) throws
            IOException {
        String[] arguments = new String[]{getMIBuildPath(),artifactType, command, artifactName};
        return runCommandWithArgs(arguments);
    }

    private static List<String> runCommandWithArgs(String[] arguments) throws IOException {

        Process process = runMiCommand(arguments);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    /**
     *
     * @param args
     * @return
     * @throws IOException
     */
    private static Process runMiCommand(String... args) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(args);
        return builder.start();
    }


}






