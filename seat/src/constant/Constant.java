package constant;

public class Constant {

    /**
     * 设置窗口相关
     */
    public static class Settings {
        /**
         * 设置窗口标题
         */
        public static final String SETTING_WINDOW_TITLE = "休息小助手";

        /**
         * 窗体模型选择器提示文本
         */
        public static final String SETTING_MODEL_SELECTOR_TEXT = "请选择模型:";

        /**
         * 窗体时间选择器提示文本
         */
        public static final String SETTING_TIME_SELECTOR_TEXT = "请选择提醒周期（单位：分钟）:";

        /**
         * 是否开启锁屏
         */
        public static final String SETTING_LOCK_WINDOWS = "是否强制锁屏功能:";

        /**
         * 鼠标检测
         */
        public static final String SETTING_MOUSE_CHECK = "是否开启鼠标停留检测:";

        /**
         * 初始化时间的可选数组
         */
        public static final String[] TIME_SELECT_ARRAY = new String[]{"1", "30", "40", "60"};

        /**
         * 锁屏值
         */
        public static final String[] LOCK_SELECT_ARRAY = new String[]{"是", "否"};

        /**
         * 鼠标
         */
        public static final String[] MOUSE_SELECT_ARRAY = new String[]{"是", "否"};
    }

    /**
     * 停止窗口相关
     */
    public static class Stop {
        /**
         * title
         */
        public static final String TITLE = "提示";

        /**
         * 成功正文
         */
        public static final String SUCCESS_TEXT = "停止成功！";

        /**
         * 失败正文
         */
        public static final String FAIL_TEXT = "停止失败！";
    }


    public static class Infor {
        /**
         * title
         */
        public static final String TITLE = "久坐提醒";

        /**
         * 鼠标停留时间
         */
        public static final int STOP_TIME = 10 * 60 * 1000;
        /**
         * 休息时间
         */
        public static final int REST_TIME = 5 * 60 * 1000;
        /**
         * 再战时间
         */
        public static final int FIGHT_TIME = 5 * 60 * 1000;


        public static final String[] HARM = new String[]{
                "久坐状态下容易造成心肌萎缩、动脉硬化，甚至诱发冠心病、心肌梗塞等心脑血管疾病。",
                "久坐会造成大脑供血不足，损伤大脑健康，表现为反应迟钝、疲困乏力、记忆力减退等。",
                "久坐会增加患老年痴呆的风险，长时间久坐还会促进血栓的形成，堵塞脑血管，诱发中风。",
                "久坐会降低细胞对胰岛素的敏感性，导致胰腺不断分泌胰岛素，影响体内糖代谢循环，容易诱发糖尿病等疾病",
                "久坐会影响臀部的血流循环，加上臀部容易出汗，湿气排出不畅，极易引起真菌感染，使臀部皮肤过敏或长湿疹。",
                "久坐会压迫男性的前列腺，使前列腺血流循环不畅，代谢产物难以排出，就会堵塞前列腺管，导致前列腺充血、淤血，诱发前列腺炎。",
                "久坐状态下热量难以消耗，就会变成脂肪堆积在体内，使体重增加，引发肥胖，甚至加大患慢性病的几率。"
        };

    }

    /**
     * 提示窗相关
     */
    public enum AlertDialog {
        /**
         * 第一个运动
         */
        S_1("/icons/01.gif", "运动一下", "原地踏步的时候，手掌向上朝天花板的方向推出去，手掌收回来的时候大拇指差不多要碰到你的肩膀。通过同时托举水瓶可以加大难度。"),

        /**
         * 第二个运动
         */
        S_2("/icons/02.gif", "运动一下", "两手叉腰，左腿向左侧滑动，上身跟着移动，最大跨度后，腿部弯曲下蹲，右腿保持伸直。归位，向右再来一遍"),

        /**
         * 第三个运动
         */
        S_3("/icons/03.gif", "运动一下", "两手向左挥动手臂高过你的头顶，换腿的时候手臂向下，然后向右再来一遍。高喊 哈利路亚！自己决定哦。"),

        /**
         * 第四个运动
         */
        S_4("/icons/04.gif", "运动一下", "站直或坐直，将右手手背抵在腰上，将左手举过头顶，放在头上；左手慢慢地把头拉向左肩，伸展脖子右侧的肌肉"),

        /**
         * 第五个运动
         */
        S_5("/icons/05.gif", "运动一下", "站直或坐直，努力向下收紧两侧肩胛骨，就像肩胛骨之间有一支铅笔需要抓住一样。这个过程中，要避免耸肩。保持5秒钟，然后放松，重复10次。你应该能感觉到肩膀上斜方肌的拉伸。"),
        S_6("/icons/06.gif", "运动一下", "在原地行进时，弯腰大约与地面成45度。弯曲你的肘部，然后把它们伸到后面，就像你在举重一样"),
        S_7("/icons/07.gif", "运动一下", "保持行进的同时，身体向前倾斜。肘部弯曲、拳头放在前面，把你的手臂像翅膀一样向后移动。尽量碰到你的肩胛骨。"),
        S_8("/icons/08.gif", "运动一下", "手肘处弯曲胳膊。当你伸直手臂时，将一只脚伸到臀部，这样当你的脚抬起时，你的手就会下降。"),
        S_9("/icons/09.gif", "运动一下", "确保你面前有空间。弯腿成半蹲，你的手臂在你身后，然后跳跃，摆动你的手臂，就像你在庆祝。"),
        S_10("/icons/10.gif", "运动一下", "两条腿左右摇摆，同时交替手臂向身体两侧拳击。为了减少肘部的压力，尽量不要把手臂伸太直"),
        S_11("/icons/11.gif", "蔡徐坤喊你运动了", "跟着坤老师一起运动"),
        S_12("/icons/12.gif", "蔡徐坤喊你运动了", "跟着坤老师一起运动"),
        S_13("/icons/13.gif", "马老师喊你来运动了", "跟着马老师的节奏，一起强身健体");


        /**
         * 装载图片路径
         */
        String imagePath;

        /**
         * 标题文本
         */
        String title;

        /**
         * 提示语文本
         */
        String text;

        AlertDialog(String imagePath, String title, String text) {
            this.imagePath = imagePath;
            this.title = title;
            this.text = text;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }
    }
}
