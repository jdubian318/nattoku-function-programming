public class Main {
    public static void main(String[] args) {
        System.out.println(calculateScore("imperative") == 9);
        System.out.println(calculateScore("no") == 2);
        System.out.println(wordScore("declarative") == 9);
        System.out.println(wordScore("yes") == 3);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static char getFirstCharacter(String s) {
        return s.charAt(0);
    }

    public static int divide(int a, int b) {
        return a / b;
    }

    /*
    public static void eatSoup(Soup soup) {
        // TODO: 「スープを飲む」アルゴリズム
    }
    */

    public static int increment(int x) {
        // xをインクリメント
        return x + 1;
    }

    public static String concatenate(String a, String b) {
        // aとbを連結する
        return a + b;
    }

    public static int calculateScore(String word) {
        int score = 0;
        for (char c : word.toCharArray()) {
            //score++;
            // ↓
            if (c != 'a') {
                score++;
            }
        }
        return score;
    }

    public static int wordScore(String word) {
        //return word.length();
        // ↓
        return word.replace("a", "").length();
    }
}