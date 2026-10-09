package net.meteorneo.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Chinese display-name map for modules and categories.
 * The chat commands (.ModuleName) still use the English names.
 */
public final class ZhNames {

    private static final Map<String, String> CAT = new HashMap<>();
    private static final Map<String, String> MOD = new HashMap<>();

    static {
        CAT.put("Combat", "战斗");
        CAT.put("Movement", "移动");
        CAT.put("Render", "渲染");
        CAT.put("Player", "玩家");
        CAT.put("World", "世界");
        CAT.put("Misc", "杂项");

        MOD.put("AimAssist", "瞄准辅助");
        MOD.put("AnchorAura", "锚点光环");
        MOD.put("AntiAim", "反瞄准");
        MOD.put("AntiAnvil", "防铁砧");
        MOD.put("AntiBed", "防床炸");
        MOD.put("AutoArmor", "自动盔甲");
        MOD.put("AutoCity", "自动挖城");
        MOD.put("AutoSword", "自动剑");
        MOD.put("AutoTotem", "自动图腾");
        MOD.put("AutoWeapon", "自动武器");
        MOD.put("AutoWeb", "自动蛛网");
        MOD.put("BedAura", "床炸光环");
        MOD.put("BowAim", "弓瞄准");
        MOD.put("Criticals", "暴击");
        MOD.put("CrystalAura", "水晶光环");
        MOD.put("FastBow", "快速拉弓");
        MOD.put("HoleFiller", "补洞");
        MOD.put("KillAura", "杀戮光环");
        MOD.put("SelfTrap", "自围");
        MOD.put("Surround", "包围");

        MOD.put("AirJump", "空中跳跃");
        MOD.put("AntiVoid", "防虚空");
        MOD.put("AutoJump", "自动跳跃");
        MOD.put("AutoWalk", "自动行走");
        MOD.put("Blink", "闪烁");
        MOD.put("BunnyHop", "兔子跳");
        MOD.put("ElytraFly", "鞘翅飞行");
        MOD.put("Flight", "飞行");
        MOD.put("FreeLook", "自由视角");
        MOD.put("LongJump", "远跳");
        MOD.put("NoFall", "无摔落");
        MOD.put("NoSlow", "减速免疫");
        MOD.put("Parkour", "跑酷");
        MOD.put("SafeWalk", "安全行走");
        MOD.put("Scaffold", "脚手架");
        MOD.put("Spider", "蜘蛛爬墙");
        MOD.put("Speed", "加速");
        MOD.put("Sprint", "疾跑");
        MOD.put("Step", "阶梯");
        MOD.put("Strafe", "侧移");

        MOD.put("ArrowESP", "箭矢ESP");
        MOD.put("ArrowTracer", "箭矢迹线");
        MOD.put("BlockHighlight", "方块高亮");
        MOD.put("BlockOutline", "方块轮廓");
        MOD.put("BreakESP", "挖掘ESP");
        MOD.put("Breadcrumbs", "足迹");
        MOD.put("ChestESP", "箱子ESP");
        MOD.put("ChinaHat", "顶环");
        MOD.put("ChunkESP", "区块ESP");
        MOD.put("CityESP", "挖城ESP");
        MOD.put("ESP", "实体ESP");
        MOD.put("FOV", "视场角");
        MOD.put("Fullbright", "全亮");
        MOD.put("HoleESP", "陷阱洞ESP");
        MOD.put("ItemESP", "物品ESP");
        MOD.put("LogoutSpots", "退出点");
        MOD.put("PlayerESP", "玩家ESP");
        MOD.put("RedESP", "红石ESP");
        MOD.put("Search", "搜索");
        MOD.put("StorageESP", "容器ESP");
        MOD.put("Tracer", "迹线");
        MOD.put("Trail", "拖尾");

        MOD.put("AntiAFK", "防挂机");
        MOD.put("AntiLevitation", "防漂浮");
        MOD.put("AutoEat", "自动进食");
        MOD.put("AutoReconnect", "自动重连");
        MOD.put("AutoRespawn", "自动重生");
        MOD.put("AutoSteal", "自动偷取");
        MOD.put("AutoTool", "自动工具");
        MOD.put("ChestStealer", "箱子窃取");
        MOD.put("EnderChest", "末影箱");
        MOD.put("FastUse", "快速使用");
        MOD.put("GhostHand", "幽灵手");
        MOD.put("InventorySort", "背包整理");
        MOD.put("MiddleClick", "中键");
        MOD.put("NoBreakDelay", "无破坏延迟");
        MOD.put("NoInteract", "免交互");
        MOD.put("Reach", "触及距离");
        MOD.put("Sneak", "潜行");
        MOD.put("XCarry", "跨背包");

        MOD.put("AirPlace", "空气放置");
        MOD.put("AntiCactus", "防仙人掌");
        MOD.put("AutoBreed", "自动繁殖");
        MOD.put("AutoFarm", "自动耕种");
        MOD.put("AutoMine", "自动挖矿");
        MOD.put("AutoTrap", "自动陷阱");
        MOD.put("BaseFinder", "基地查找");
        MOD.put("ChunkLoader", "区块加载");
        MOD.put("HighwayBuilder", "公路建造");
        MOD.put("InstantMine", "瞬挖");
        MOD.put("Nuker", "核弹");
        MOD.put("Portals", "传送门");
        MOD.put("Reap", "收割");
        MOD.put("SpawnProofer", "防刷怪");
        MOD.put("StashFinder", "储物查找");

        MOD.put("Announcer", "公告");
        MOD.put("AutoFish", "自动钓鱼");
        MOD.put("AutoLog", "自动下线");
        MOD.put("Binds", "绑定");
        MOD.put("ClickGUI", "图形界面");
        MOD.put("Commands", "命令");
        MOD.put("Friend", "好友");
        MOD.put("Notifications", "通知");
        MOD.put("Profiler", "分析器");
        MOD.put("Spammer", "刷屏");
        MOD.put("Discord", "Discord");
        MOD.put("Panic", "恐慌");
    }

    private ZhNames() {
    }

    public static String cat(String en) {
        String z = CAT.get(en);
        return z != null ? z : en;
    }

    public static String mod(String en) {
        String z = MOD.get(en);
        return z != null ? z : en;
    }
}
