# MeteorNeoForge — NeoForge 1.21.1 模块化客户端模组（Meteor 风格复刻）

从零实现的 NeoForge 1.21.1 模组，复刻 Meteor Client 的模块化架构。
**当前里程碑：模块系统框架 + Movement 分类的 3 个真实功能模块（Flight / Speed / Sprint）。**

> 注意：这是 Meteor 功能体系的**起点骨架**，不是完整 Meteor 复刻。后续在现有
> 模块系统上持续添加模块即可扩展为完整客户端模组。

## 目录结构

```
MeteorNeoForge/
├── build.gradle              # NeoForge 构建配置（moddev 插件，Java 21）
├── settings.gradle           # 仓库配置（含国内镜像）
├── gradle.properties         # Gradle 参数
└── src/main/
    ├── java/net/meteorneo/
    │   ├── MeteorNeoForge.java              # @Mod 主入口，订阅客户端 Tick
    │   ├── core/
    │   │   ├── Category.java                # 模块分类（Combat/Movement/Render/Player/World/Misc）
    │   │   ├── Module.java                  # 模块基类（toggle/enable/disable/tick）
    │   │   └── Modules.java                 # 模块注册表
    │   └── systems/modules/movement/
    │       ├── Flight.java                  # 飞行
    │       ├── Speed.java                   # 加速
    │       └── Sprint.java                  # 强制疾跑
    └── resources/META-INF/
        └── neoforge.mods.toml               # NeoForge 模组元数据
```

## 构建前提

1. **JDK 21**（必须 64 位）。本机路径：`C:\Program Files\Microsoft\jdk-21.0.9.10-hotspot`
2. **能访问 `maven.neoforged.net`**（NeoForge 依赖仓库，国内直连常被墙；
   Steam++ 等规则加速器对此外无效，需全局代理/镜像或可达网络）。
   Gradle 首次构建会下载并反编译 Minecraft 1.21.1 源码，可能耗时 10~30 分钟。

## 构建命令

```bat
set JAVA_HOME=C:\Program Files\Microsoft\jdk-21.0.9.10-hotspot
gradle build        // 用已安装的 Gradle 8.8+
```
或使用项目 Gradle wrapper（首次会下载对应 Gradle）：
```bat
gradlew.bat build
```

产物在 `build/libs/meteor-neoforge-0.1.0.jar`。

## 安装

把生成的 `meteor-neoforge-0.1.0.jar` 放入 **Minecraft 1.21.1 + NeoForge** 客户端实例的
`mods` 文件夹，启动即可。当前模块为 Flight / Speed / Sprint（暂以硬编码方式启用，
后续里程碑将加入模块开关命令与 GUI）。

## 技术要点

- 使用官方 **ModDevGradle（`net.neoforged.moddev`）** 插件，`neoForge.version = 21.1.221`
- 事件：`NeoForge.EVENT_BUS` 订阅 `ClientTickEvent.Pre`，驱动所有已启用模块的 `onTick`
- 类名基于 NeoForge 官方（Mojang）映射，如 `net.minecraft.client.player.LocalPlayer`

## 待办（后续里程碑）

- [ ] 模块开关命令（如 `#flight`）
- [ ] 设置项（Setting）系统
- [ ] ClickGUI / HUD
- [ ] 更多分类模块（Combat / Render / Player / World / Misc）
