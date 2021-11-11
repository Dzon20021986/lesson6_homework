package lesson6homework;


/*
1. Создать и запустить программу для построения двоичного дерева.
* В цикле с глубиной 100 тыс деревьев с глубиной в 6 уровней.
* Данные, которыми необходимо заполнить узлы деревьев, представляются в виде чисел типа int.
* Число, которое попадает в узел, должно генерироваться случайным образом в диапазоне от -100 до 100.
* 2. Проанализировать, какой процент созданных деревьев являются несбалансированными.
 */

public class Main {

    public static void main(String[] args) {
 //       MyTreeMap<Integer, String>map =new MyTreeMap<>();
        // Создаем одно дерево с высотой 1
 //       TreesMaker treesMaker = new TreesMaker();

        // Создаем одинаковые деревья с высотой 6

        int numberOfTrees = 100000;  // количество деревьев
        int maxThreeHeight = 6; // заданная высота деревьев
        TreesMaker treesMaker = new TreesMaker(numberOfTrees, maxThreeHeight);

        // создаем деревья с разной высотой
//        int[] treesHeights = {5, 1, 3, 6, 10}; // количество деревьев
//        TreesMaker treesMaker = new TreesMaker(treesHeights);
    }
}
