package ru.job4j.recursion;

/**
 * Created by Admin on 14.08.2017.
 */
public class Exchange {
    /**
     * массив номиналов допустимых купюр.
     */
    private static int[] cointsNom = {1, 5, 10, 25, 50};

    /**
     * Рекурсивная функция подщета возможных вариантов размена.
     *
     * @param money       int количество денег для размена
     * @param indexOfCoin int максимальный индекс разменных купюр
     * @return int количество возможных вариантов размена
     */
    public static int getCountOfWays(int money, int indexOfCoin) {
        if (money < 0 || indexOfCoin < 0) {
            return 0;
        }
        if (money == 0 || indexOfCoin == 0) {
            return 1;
        }
        return getCountOfWays(money, indexOfCoin - 1) + getCountOfWays(money - cointsNom[indexOfCoin], indexOfCoin);
    }

}
