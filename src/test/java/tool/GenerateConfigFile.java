package tool;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eldath Ray on 2017/3/17.
 *
 * @author Eldath Ray
 * @since v0.0.1 Beta
 */
public class GenerateConfigFile {
    private static Map<String, Object> allConfigs = new HashMap<>();
    private static Map<String, Object> pluginAllowPeoples = new HashMap<>();
    private static Map<String, Object> pluginConfig = new HashMap<>();

    static {
        pluginAllowPeoples.put("CommandManager_basic", new long[]{951394653, 360736041, 1464443139, 704639565});
        pluginAllowPeoples.put("CommandManager_stop", new long[]{1464443139, 360736041, 704639565, 951394653, 1016281105});
        pluginAllowPeoples.put("CommandManager_restart", new long[]{1464443139, 704639565, 951394653});
        pluginAllowPeoples.put("Blacklist_basic", new long[]{1464443139});
        pluginAllowPeoples.put("Echo_basic", new long[]{1464443139});
        //
        Map<String, Object> loopUsing = new HashMap<>();
        loopUsing.put("Uid_BlackList_Enabled", true);
        loopUsing.put("BlockList_Words", new String[]{"来一炮", "寒寒", "冰冰", "冰封", "ice1000",
                "eldath", "hanhan", "男", "女", "蛤", "膜", "苟", "太短", "变长", "baka", "笨蛋", "傻瓜", "操", "艹", "fuck"});
        pluginConfig.put("GXiaoIce", loopUsing);
        // loopUsing.clear();
        //
        allConfigs.put("Follow_Group_Uid", new long[]{617118724});
        allConfigs.put("Record_Group_Uid", new long[]{399863405, 617118724});
        allConfigs.put("Mojo-Webqq_API_Address", "http://127.0.0.1:5000");
        allConfigs.put("Mojo-Webqq_POST_API_Address", "http://127.0.0.1:5050");
        allConfigs.put("Mojo-Weixin_API_Address", "http://127.0.0.1:3500");
        allConfigs.put("Admin_Uid", new long[]{1464443139});
        allConfigs.put("Debug", false);
        allConfigs.put("BlackList_Uid", new long[]{});
        allConfigs.put("plugin_allowed_account", pluginAllowPeoples);
        allConfigs.put("plugin_config", pluginConfig);
    }

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        for (Object m : allConfigs.entrySet()) {
            Map.Entry mapEntry = (Map.Entry) m;
            String key = (String) mapEntry.getKey();
            Object value = mapEntry.getValue();
            object.put(key, value);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("config.json"), StandardCharsets.UTF_8)) {
            writer.write(JSONFormatter.format(object.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
