#ZBarAndroidSDK

ZBarAndroidSDK是之前的ZBar解析二维码项目的重构和优化

之前的版本
==========

>* ZBar的编译项目： https://github.com/SkillCollege/ZBarBuildProj
>* ZBar的示例项目： https://github.com/SkillCollege/ZBarScanProj


此次做的更新有
==============

>* 使用了最新版本的libiconv库进行编译，版本号：1.14；下载地址： http://www.gnu.org/software/libiconv/
>* 重新修改ZBar对中文的支持，并且可以解析GBK，UTF-8格式生成的二维码图片
>* 使用最新的ndk-r10d进行ndk-build，支持64位cpu

##License

```java
/*
 * Copyright (C) 2014 Chen Tao <1076559197@qq.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
```
