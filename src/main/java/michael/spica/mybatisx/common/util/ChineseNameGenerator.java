package michael.spica.mybatisx.common.util;

import cn.hutool.core.util.RandomUtil;

import java.util.List;

/**
 * Created by michael on 2025-10-23.
 */
public class ChineseNameGenerator {

    // 常见姓氏（可自行扩充）
    private static final String[] LAST_NAMES = {
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨",
            "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜",
            "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "鲁"
    };

    // 常见男名
    private static final String[] MALE_NAMES = {
            "伟", "强", "磊", "刚", "杰", "涛", "超", "明", "军", "勇", "斌", "鹏", "华", "波", "亮", "栋", "帅", "翔", "鹏飞", "子轩"
    };

    // 常见女名
    private static final String[] FEMALE_NAMES = {
            "芳", "娜", "敏", "静", "丽", "娟", "艳", "霞", "莹", "怡", "婷", "梅", "倩", "雪", "慧", "晶", "曼", "琳", "佳", "悦"
    };

    // 混合名备用汉字（用于随机生成名字的第二个字）
    private static final String FIRST_NAME_CHARS =
            "一乙二丁卉凡云兰可平玉文宁心冰芝秋青荣珍芬素英洁梅莉香月珊雪雅倩涵梦娴慧曼玲霞辉超强军杰涛明丽娜芳静伟鹏飞子轩";

    /**
     * 随机生成一个中文姓名（男女随机）
     */
    public static String randomName() {
        return randomName(RandomUtil.randomBoolean());
    }

    /**
     * 根据性别生成中文姓名
     *
     * @param male 是否男生
     */
    public static String randomName(boolean male) {
        String lastName = RandomUtil.randomEle(LAST_NAMES);
        String firstName;
        if (male) {
            firstName = RandomUtil.randomEle(MALE_NAMES);
        } else {
            firstName = RandomUtil.randomEle(FEMALE_NAMES);
        }

        // 随机决定是否加一个字（双名）
        if (RandomUtil.randomDouble(0, 1) < 0.3) {
            firstName += RandomUtil.randomChar(FIRST_NAME_CHARS);
        }

        return lastName + firstName;
    }

    /**
     * 批量生成中文姓名
     *
     * @param count 生成数量
     * @param male  是否男生
     */
    public static List<String> randomNames(int count, boolean male) {
        return RandomUtil.randomEleList(
                        java.util.Arrays.asList(LAST_NAMES),
                        count
                ).stream()
                .map(lastName -> lastName + (male ?
                        RandomUtil.randomEle(MALE_NAMES) : RandomUtil.randomEle(FEMALE_NAMES)))
                .toList();
    }

    /**
     * ✅ 批量生成随机性别的姓名
     *
     * @param count 数量
     */
    public static List<String> randomNames(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> randomName()) // 性别随机
                .toList();
    }
}
