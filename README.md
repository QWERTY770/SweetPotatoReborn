<p align="center">
  <img src="common/src/main/resources/assets/spmreborn/icon.png" alt="烤地瓜：重生图标" width="128">
</p>

# 烤地瓜：重生 · Sweet Potato Reborn

让一颗地瓜，连接田园生活与魔法。

[![Minecraft](https://img.shields.io/badge/Minecraft-26.2-62B47A)](gradle.properties)
[![平台](https://img.shields.io/badge/Loader-Fabric%20%7C%20NeoForge-orange)](#下载与安装)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue)](LICENSE)
[![Modrinth](https://img.shields.io/badge/Download-Modrinth-00AF5C)](https://modrinth.com/mod/spmreborn)

**烤地瓜：重生（SPR）** 是围绕地瓜、农耕与魔法展开的 Minecraft Java 版模组，基于原版 **Sweet Potato Mod（SPM）** 延续开发。种下紫薯、红薯和白薯，烤一份热腾腾的地瓜，再用地瓜粉和地瓜皮探索附魔作物与魔法锅的秘密。

[下载模组](https://modrinth.com/mod/spmreborn/versions) · [MC 百科与玩法资料](https://www.mcmod.cn/class/17173.html) · [源代码](https://github.com/QWERTY770/SweetPotatoReborn) · [问题反馈](https://github.com/QWERTY770/SweetPotatoReborn/issues) · [交流与贡献](#反馈与贡献)

## 玩法特色

- **三种地瓜，三种形态**：紫薯、红薯和白薯各有生地瓜、烤地瓜与附魔地瓜形态，可用于种植、食用和加工。
- **从农田走向魔法**：研磨地瓜获得地瓜粉，用农林台升级作物与树苗，再用魔法锅转换或附魔地瓜。
- **吃完也有用途**：食用地瓜留下的地瓜皮可以为魔法锅提供燃料；地瓜粉还能改善魔法锅的转换结果。
- **附魔农场**：培育附魔小麦、胡萝卜、马铃薯、甜菜、甘蔗，以及橡树、云杉、白桦、丛林、金合欢和深色橡树的附魔树苗。
- **探索与收获**：从怪物掉落和结构宝箱中发现地瓜，解锁“香甜软糯”“灵魂魔法”等进度。

### 三种工作方块

| 方块 | 用途 | 关键材料或条件 |
| --- | --- | --- |
| 研磨器 | 将生地瓜或附魔地瓜加工为地瓜粉 | 投入原料，累计研磨数值达到 15 后开始加工 |
| 农林台 | 将支持的普通作物、树苗升级为附魔变种 | 对应作物或树苗 + 地瓜粉 |
| 魔法锅 | 转换地瓜品种，或制作带有状态效果的附魔地瓜 | 灵魂火激活，以地瓜皮为燃料，可添加地瓜粉 |

### 从第一颗地瓜开始

1. **获取**：击杀僵尸、尸壳或僵尸村民，有机会获得生地瓜；也可以在部分结构的宝箱中寻找。
2. **种植与烹饪**：将生地瓜种在耕地上扩大收成，或使用熔炉、烟熏炉、营火烤熟食用。记得留下地瓜皮。
3. **加工**：用研磨器制作地瓜粉，再到农林台升级作物与树苗。
4. **施展魔法**：在魔法锅下方一层的 3×3 范围内点燃灵魂火，放入生地瓜与地瓜皮，尝试转换与附魔。

收获附魔作物、回收已种下的附魔树苗时，使用带有**精准采集**的工具才能保留其附魔变种。完整物品资料和玩法说明见 [MC 百科](https://www.mcmod.cn/class/17173.html)。

## 下载与安装

从 [Modrinth 版本列表](https://modrinth.com/mod/spmreborn/versions)选择与你的 **Minecraft 版本和模组加载器**一致的文件。

| 加载器 | 必需前置 |
| --- | --- |
| Fabric | [Architectury API](https://modrinth.com/mod/architectury-api)、[Fabric API](https://modrinth.com/mod/fabric-api) |
| NeoForge | [Architectury API](https://modrinth.com/mod/architectury-api) |

1. 安装对应版本的 Fabric 或 NeoForge。
2. 将模组及所需前置的 `.jar` 文件放入游戏实例的 `mods` 文件夹；前置也须匹配游戏版本和加载器。
3. 启动游戏。多人游戏需要在**客户端和服务端同时安装**本模组及对应前置。

### 当前分支

本分支面向 **Minecraft 26.2**，构建版本为 **SPR 1.2.1+26.2**，使用 **Java 25**。以下为当前源码采用的依赖版本，更新时以 [gradle.properties](gradle.properties) 为准：

| 依赖 | 构建版本 |
| --- | --- |
| Fabric Loader | 0.19.3 |
| Fabric API | 0.154.1+26.2 |
| NeoForge | 26.2.0.8-beta |
| Architectury API | 21.0.2 |

项目的历史发行版还覆盖 Minecraft 1.20–1.20.1、1.21–1.21.4，部分版本提供 Forge 支持。本分支包含 Fabric 与 NeoForge 两个平台模块；旧版游戏请在下载页选择对应发行版，具体兼容范围以该文件的说明为准。

## 从源码构建

准备 **JDK 25**，并让 `JAVA_HOME` 指向该 JDK。克隆本仓库、切换到 `26.2` 分支后，在仓库根目录使用随仓库提供的 Gradle Wrapper；首次运行需要联网下载 Gradle 和构建依赖。

Windows PowerShell：

```powershell
.\gradlew.bat :fabric:shadowJar :neoforge:shadowJar
```

Linux / macOS：

```bash
bash ./gradlew :fabric:shadowJar :neoforge:shadowJar
```

两个 `shadowJar` 任务会分别编译、打包平台代码及共享模块。生成的模组位于：

```text
fabric/build/libs/sweet-potato-reborn-fabric-<模组版本>+<MC版本>.jar
neoforge/build/libs/sweet-potato-reborn-neoforge-<模组版本>+<MC版本>.jar
```

按需只运行其中一个平台的任务即可。开发时可将仓库作为 Gradle 项目导入 IDE，并将项目 SDK 与 Gradle JVM 均设置为 JDK 25。

### 仓库结构

| 目录 | 内容 |
| --- | --- |
| [`common/`](common/) | 共享游戏逻辑、方块与物品、配方、战利品表、纹理和翻译 |
| [`fabric/`](fabric/) | Fabric 入口、平台实现及模组元数据 |
| [`neoforge/`](neoforge/) | NeoForge 入口、平台实现及模组元数据 |
| [`gradle/`](gradle/) | Gradle Wrapper 配置 |

## 反馈与贡献

欢迎提交问题、改进建议、翻译和 Pull Request。

**模组开发交流 QQ 群：912759411**。欢迎加入群聊，交流玩法、反馈问题和体验最新测试版本。

- **报告问题**：请前往 [GitHub Issues](https://github.com/QWERTY770/SweetPotatoReborn/issues)，先检索是否已有相同问题。报告时请提供 Minecraft 版本、加载器及版本、SPR 与前置版本、复现步骤、预期与实际表现，以及相关日志或崩溃报告。
- **贡献代码**：针对对应 Minecraft 分支修改，说明变更目的及验证方式。共享玩法逻辑位于 `common`，平台差异放在对应加载器模块中。
- **完善翻译**：语言文件位于 [`common/src/main/resources/assets/spmreborn/lang/`](common/src/main/resources/assets/spmreborn/lang/)。可通过 PR 提交，也可从 [官方 Modrinth 页面](https://modrinth.com/mod/spmreborn)进入作者提供的 Crowdin 翻译入口。
- **补充资料**：欢迎在 [MC 百科](https://www.mcmod.cn/class/17173.html)完善物品信息与玩法教程。

## 致谢与许可

感谢原版 Sweet Potato Mod 的开发者 **teddyxlandlee、Rayawa、Peterice**，以及 SPR 作者 **QWERTY770**、贡献者 **LinGCar、雨音浅夏**和所有参与翻译、测试与反馈的玩家。

本项目采用 **[Apache License 2.0](LICENSE)**，完整许可条款见仓库中的 `LICENSE`。如计划将本模组加入整合包，欢迎通过 QQ 群联系作者；相关说明见 [Modrinth 页面](https://modrinth.com/mod/spmreborn)。
