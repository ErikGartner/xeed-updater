# XEED Updater
*This is a self-contained update module for the [XEED desktop](https://github.com/ErikGartner/xeed-legacy) application*.

## Usage
This module intended to bundled with XEED as resource and not intended for standalone usage.

It is executed with the following arguments by XEED:
```
java -jar xeed-updater [download url] [xeed.jar path]
```

### Building
The project uses maven for building executable jars with bundled dependencies.
```
mvn package
```
It will generate a ```target/``` folder with the compiled jars.

## Tests
None at the moment but they should be stored in ```src/test/java/```.

## Contributions
Pull requests are welcome. Main developer is @erik_gartner.

### Problems
This project is very old and suffers from incorrect code conventions, bad patterns and a tangled structure. Fixing these problem should be considered high priority before developing new features.

Despite these problem the program is generally stable and extended usage over several years have only revealed minor bugs.

## License
Copyright 2015 Erik Gärtner

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
