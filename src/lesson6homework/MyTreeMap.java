package lesson6homework;

import com.sun.jdi.Value;

import java.security.Key;
import java.util.NoSuchElementException;

public class MyTreeMap<Key extends Comparable<Key>, Value> {
    private Node root; // корневой узел - вершина дерева.

    /**
     * Внутренний класс определяющий узлы дерева
     */

    private class Node {
        Key key; //ключ узла.
        Value value; // значение узла.
        Node left; // левый потомок узла.
        Node right; // правый потом узла.
        int size; // размер дерева.
        int height; // высота дерева
        boolean balance; // сбалансированность дерева

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;  // как только создали корень, размер дерева стал 1.
            height = 0; // при создании любого узла - его высота равна 0.
            balance = true; // при создании любого узла он считается сбалансированным.
        }
    }

    // Публичный метод начала получения размера дерева
    // return - размер дерева
    public int size() {
        // запускаем рекурсивный метод получения размера, начиная с корня
        return size(root);
    }

    // Рекурсивный метод получения размера дерева
    // node - текущий узел(вершина)
    // return - размер текущего поддерева(узла)

    private int size(Node node) {
        // базовый случай рекурсии, дошли до нулевого узла, возвращаем 0.
        if (node == null) {
            return 0;
        }
        // возвращаем размер текущего поддерева.
        return node.size;
    }

    // Публичный метод неачала получения высоты дерева
    // return размер дерева
    int height() {
        return height(root);
    }

    // Рекурсивный метод полдучения высоты дерева
    // node текущий узел(вершина)
    // return высота текущего поддерева(узла)
    private int height(Node node) {
        // базовый случай рукурсии, дошли до нулевого узлаили узла нулевой высоты, возвращаем 0
        if (node == null) {  // начальный случай, когда даже root еще не создан
            return 0;
        }
        // возвращаем высоту текущего поддерева
        return node.height;
    }

    // публичный метод начала получения сбалансированного дерева
    // return true -  дерево сбалансированно( разница высот его детей не превышает 1)
    boolean isBalanced() {
        return isBalanced(root);
    }

    // Рекурсивный метод получения сбалансированности дерева
    // node - текущий узел
    // return true -  дерево сбалансированно( разница высот его детей не превышает 1)
    private boolean isBalanced(Node node) {
        // базовый случай рекурсии, дошли до нулевого узла или узла нулевой высоты, возвращаем 0
        if (node == null) { // начальный случай, когда даже root  еще не создан
            return true;
        }
        // если любой узел потомок несбалансирован, то все дерево - несбалансировано
        // возвращаем сбалансированность текущего поддерева
        return node.balance;
    }

    // Метод проверки не пустое ли дерево
    // return true - дерево пустое(корня нет)
    public boolean isEmpty() {
        return root == null;
    }

    // Метод проверки не пустой ключ
    // key -  проверяемый ключ
    private void checkKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key null");
        }
    }

    // Публичный метод поиска, содержиться ли элемент в дереве
    // key - ключ проверяемого элемента
    // return true - узел с таким клем есть в дереве

    public boolean contains(Key key) {
        // запускаем рекурсивный метод поиска узла по ключу, начиная с корня.
        return get(key) != null;
    }

    // Публичный метод поиска узла по ключу
    // key - ключ проверяемого элемента
    // return - значение найденного элемента
    public Value get(Key key) {
        //  запускаем рекурсивный метод поиска узла по ключу, начиная с корня.
        checkKeyNotNull(key);
        return get(root, key);
    }

    // Рекурсивный метод поиска узла по ключу
    // node - текущий (проверяемый) узел
    // key - ключ узла, который ищем
    // return значение найденого узла
    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }

    }

    // Публичный метод  добавления узла в дерево
    // key - ключ узла на добавление
    // value - значение узла на добавление
    public void put(Key key, Value value) {
        // проверяем не пустой ли ключ
        checkKeyNotNull(key);
        if (value == null) {
            //delete(key)
            return;
        }
        // Запускаем рекурсивный метод добавления узла, начиная с корня
        root = put(this.root, key, value);
    }

    // Рекурсивный метод добавления узла в дерево
    // node - текущий узел на добавление
    // key - ключ узла на добавление
    // value - значение узла на добавление
    // return - добавленный узел

    private Node put(Node node, Key key, Value value) {
        // базовый случай рекурсии
        if (node == null) {
            // возвращаем новый узел с ключем и значением
            return new Node(key, value);
        }
        // запоминаем результат сравнения искомого ключа с текущим ключем
        int cmp = key.compareTo(node.key);
        // если искомый ключ - это текущий (нашли узел с таким же ключем
        if (cmp == 0) {
            // просто меняем у узла значение на новое
            node.value = value;
            // если искомый ключ меньше текущего
        } else if (cmp < 0) {
            // ищем в ветке с левым потомком
            node.left = put(node.left, key, value);
            // если искомый ключ больше текущего
        } else {
            // ищем в ветке с правым потомком
            node.right = put(node.right, key, value);
        }
        // пересчитываем размер дерева
        node.size = 1 + size(node.left) + size(node.right);
        // возвращаем добавленный узел
        return node;
    }

    // Публичный метод поиска узла с самым маленьким ключем
    // return - узел с наименьшим ключем

    public Key minKey() {
        return min(root).key;
    }

    // Рекурсивный метод поиска узла с самым маленьким ключем
    // node - текущий (проверяемый) узел
    // return - текущий узел
    private Node min(Node node) {
        // базовый метод рекурсии, возвращаем текущий узел, если дошли до конца
        if (node.left == null) {
            return node;
        }
        // идем дальше в лево
        return min(node.left);
    }

    // Публичный метод поиска узла с самым большим ключем
    // return - узел с самым большим ключем
    public Key maxKey() {
        return max(root).key;
    }

    // Рекурсивный метод поиска с самым большшим ключем
    // node - текущий (проверяемый) узел
    // return - текущий узел
    private Node max(Node node) {
        // базовый слукчай рекурсии, возвращаем текущий узел, если дошли до конца
        if (node.right == null) {
            return node;
        }
        // идем дальше вправо
        return max(node.right);
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        deleteMin(root);
    }
    // Рекурсивный метод удаления узла с самым маленьким ключем.
    // node - проверяемый узел.
    // return - текущий узел.

    private Node deleteMin(Node node) {
        // базовый случай рекурсии, если слева пусто.
        if (node.left == null) {
            // возвращаемый правого потомка.
            return node.right;
        }
        // рекурсивно идем все левее и левее пока не дойдем до конца
        // и удаляем узел с самым маленьким ключем ( в идеале самый левый)
        node.left = deleteMin(node.left);
        // пересчитываем  размер дерева после удаления узла.
        node.size = 1 + size(node.left) + size(node.right);
        // пересчитываем размер, высоту и сбалансированность дерева
        recalculate(node);
        // возвращаем удаленный узел.
        return node;
    }

    // публичный метод удаления узла.
    // key - ключ узла на удаление
    public void delete(Key key) {
        // проверяем не пустой ли ключ
        checkKeyNotNull(key);
        // запускаем рекурсивный метод удаления
        root = delete(root, key);
    }

    // рекурсивный метод удаления узла дерева.
    // node - текущий узел
    // key - ключ узла на удаление
    // return - удаленный узел

    private Node delete(Node node, Key key) {
        // базовый случай рекурсии - дошли до конца ветки
        if (node == null) {
            // возвращаем null, если узел пустой
            return null;
        }
        // запоминаем результат сравнения искомого ключа с текущим ключем
        int cmp = key.compareTo(node.key);
        // если искомый ключ меньше текущего
        if (cmp < 0) {
            // идем в левое плечо
            node.left = delete(node.left, key);
            // если искомый ключ больше текущего
        } else if (cmp > 0) {
            // идем в правое плечо
            node.right = delete(node.right, key);
            // они равны - нашли узел для удаления
        } else {
            // если у узла нет левого плеча
            if (node.left == null) {
                // возвращаем правый узел
                return node.right;
            }
            // если у узла нет правого плеча
            if (node.right == null) {
                // возвращаем левый узел
                return node.left;
            }
            // если у удаляемого узла есть оба потомка, то меняем его на самый маленький в правом плече
            // запоминаем удаляемый узел
            Node temp = node;
            // меняем узел на самый маленький в правом плече
            node = min(node.right);
            // ппрописываем у него ссылку на правого потомка
            node.right = deleteMin(temp.right);
            // прописываем у него ссылку на левого потомка
            node.left = temp.left;
        }
        // пересчитываем размер дерева
        node.size = 1 + size(node.left) + size(node.right);
        // пересчитываем размер, высоту и сбалансированность
        recalculate(node);
        // возвращаем удаленный узел, если нашил
        return node;
    }

    // Метод пересчета размера, высоты и балансировки дерева
    // node - текущий узел
    private void recalculate(Node node) {
        calculateSize(node);
        calculateHeightAndBalance(node);
    }

    // Рекурсивный метод расчета размера дерева
    // node - текущий узел
    private void calculateSize(Node node) {
        // пересчитываем размер дерева
        node.size = size(node.left) + size(node.right) + 1;
    }

    // Рекурсивный метод расчет высоты и балансадерева
    // node - текущий узел
    private void calculateHeightAndBalance(Node node) {
        // пересчитываем высоты детей дерева
        int leftH = height(node.left);
        int rightH = height(node.right);
        // пересчитываем высоту самого дерева
        calculateHeight(node, leftH, rightH);
        // если высота одного из деревьев отличается от другого больше, чем на 1, дерево несбалансировано

    }

    // Метод расчета высота дерева
    // node - текущий узел
    private void calculateHeight(Node node, int leftH, int rightH) {
        // высота равна высоте самого большого плеча + 1;
        node.height = Math.max(leftH, rightH) + 1;
    }

    private void calculateBalance(Node node, int leftH, int rightH) {
        node.balance = !(Math.abs(leftH - rightH) > 1);
    }

    // начинает вывод элементов дерева, начиная с корня, далее рекурсивно выводятся остальные элементы
    @Override
    public String toString() {
        return toString(root);
    }

    // рекурсивный метод вывода элементов (узлов) дерева
    private String toString(Node node) {
        if (node == null) {
            return "";
        }
        // сначала выводим все левые, начиная с самого левого, затем правые,
        // возвращаясь от самого левого на верх
        return toString(node.left) + " " +
                node.key + " = " + node.value + " " +
                toString(node.right);
    }

}
