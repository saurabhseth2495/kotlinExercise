import java.lang.NumberFormatException

fun main() {
    var exit: Boolean = false
    var doublyLinkedList = DoublyLinkedList()
    while (!exit) {
        println()
        println("....Enter your choice....")
        println(" 1. for add element at head position.")
        println(" 2. for add element at any index position.")
        println(" 3. for add element at tail position.")
        println(" 4. for show forward list")
        println(" 5. for show backward list")
        println(" 6. for delete object at any index")
        println(" 7. for delete object of head position.")
        println(" 8. for delete object of tail position.")
        println(" 9. for get an object in particular index.")
        println(" 0. for exit")
        print("Please enter your choice  : ")
        try {
            var choice: Int = Integer.valueOf(readLine())
            when (choice) {
                0 -> exit = true
                1 -> addElementAtHeadPosition(doublyLinkedList)
                2 -> addElementAtIndexPosition(doublyLinkedList)
                3 -> addElementAtTailPosition(doublyLinkedList)
                4 -> showForwardList(doublyLinkedList)
                5 -> showBackwardList(doublyLinkedList)
                6 -> deleteElementAtIndex(doublyLinkedList)
                7 -> deleteElementOfHead(doublyLinkedList)
                8 -> deleteElementOfTail(doublyLinkedList)
                9 -> getObjectOfAnIndex(doublyLinkedList)
                else -> println("your choice is not exist.")
            }
        } catch (e: NumberFormatException) {
            println("your choice is not exist.")
        }

    }
}

fun deleteElementOfHead(doublyLinkedList: DoublyLinkedList) {
    doublyLinkedList.deleteOfHead()
}

fun deleteElementOfTail(doublyLinkedList: DoublyLinkedList) {
    doublyLinkedList.deleteOfTail()
}

fun getObjectOfAnIndex(doublyLinkedList: DoublyLinkedList) {
    print("please provide position which you want to display.")
    try {
        var index = Integer.valueOf(readLine())
        var student = doublyLinkedList.get(index)
        if (student == -1) {
            println("At this index object not found.")
            return
        }
        if (student is Student) {
            student.display()
        }
    } catch (e: Exception) {
        println("wrong input.")
    }
}

fun addElementAtTailPosition(doublyLinkedList: DoublyLinkedList) {
    doublyLinkedList.addAtTail(getValueForAnObject())
}

fun deleteElementAtIndex(doublyLinkedList: DoublyLinkedList) {
    print("please provide position which you want to delete :")
    try {
        var index = Integer.valueOf(readLine())
        doublyLinkedList.deleteAtIndex(index)
    } catch (e: Exception) {
        println("wrong input.")
    }

}

fun showBackwardList(doublyLinkedList: DoublyLinkedList) {
    doublyLinkedList.traverseBackwardList()
    if (doublyLinkedList.list.size == 0) {
        println("list has no element.")
        return
    }
    println("size of list : " + doublyLinkedList.list.size)
    for (element in doublyLinkedList.list) {
        if (element is Student) {
            var student: Student = element
            student.display()
        } else {
            println("element is not Student")
        }
    }
}

fun showForwardList(doublyLinkedList: DoublyLinkedList) {
    doublyLinkedList.traverseForwardList()
    if (doublyLinkedList.list.size == 0) {
        println("list has no element.")
    } else {
        println("size of list : " + doublyLinkedList.list.size)
        for (element in doublyLinkedList.list) {
            if (element is Student) {
                var student: Student = element
                student.display()
            } else {
                println("element is not Student")
            }
        }
    }
}

fun addElementAtIndexPosition(doublyLinkedList: DoublyLinkedList) {
    try {
        print("please enter position at which you want to add object : ")
        var index = Integer.valueOf(readLine())
        if (index > doublyLinkedList.list.size && index < 0) {
            println("Your index position is wrong.")
            return
        }
        doublyLinkedList.addAtIndex(getValueForAnObject(), index)
    } catch (e: Exception) {
        println("wrong input.")
    }

}

fun addElementAtHeadPosition(doublyLinkedList: DoublyLinkedList) {
    doublyLinkedList.addAtHead(getValueForAnObject())
}

fun getValueForAnObject(): Student? {

    return try {
        print("Please enter name of student = ")
        var name = readLine().toString()
        print("Please enter roll no. of student = ")
        var rollNo = Integer.valueOf(readLine())
        Student(name, rollNo)
    } catch (e: NumberFormatException) {
        println("your input is wrong.")
        null
    }
}

class Student(var name: String, var rollNo: Int) {
    fun display() = println("name = $name roll no.= $rollNo")
}

class DoublyLinkedList {
    private var length = 0
    var head: Node? = null
    var tail: Node? = null
    var list = mutableListOf<Any>()

    inner class Node(var element: Any?) {
        var prev: Node? = null
        var next: Node? = null
    }

    fun addAtHead(element: Any?) {
        if (element == null) {
            println("some thing went wrong , can not proceed further. add unsuccessful !")
            return
        }
        val h = this.head
        val newNode = Node(element)
        newNode.next = this.head
        head = newNode
        if (h == null) tail = newNode else h.prev = newNode
        this.length++
        println("Added one element at head position successfully.")
    }

    fun addAtTail(element: Any?) {
        if (element == null) {
            println("some thing went wrong , can not proceed further. add unsuccessful !")
            return
        }
        var t = this.tail
        val newNode = Node(element)
        newNode.prev = this.tail
        tail = newNode
        if (t == null) head = newNode else t.next = newNode

        this.length++
        println("Added one element at tail position successfully.")
    }

    fun get(index: Int): Any? {
        if (index >= this.length || index < 0) return -1
        var curr = 0
        var h = this.head
        while (curr < index) {
            h = h?.next
            curr++
        }
        return h!!.element
    }

    fun addAtIndex(element: Any?, index: Int) {
//        when element is not null then it executed.
//        element?.let {  }
        if (element == null) {
            println("some thing went wrong , can not proceed further. add unsuccessful !")
            return
        }
        if (index > this.length || index < 0) {
            println("Your index position is wrong.")
            return
        }
        if (index == 0) {
            addAtHead(element)
            return
        }
        if (index == length) {
            addAtTail(element)
            return
        }
        val newNode = Node(element)
        var h = this.head
        var curr = 0
        while (curr < index - 1) {
            h = h?.next
            curr++
        }
        val nextNode = h?.next
        newNode.next = nextNode
        nextNode?.prev = newNode
        h?.next = newNode
        newNode.prev = h
        this.length++
        println("Added one element at $index position successfully.")
    }

    fun deleteAtIndex(index: Int) {
        if (index >= length || index < 0) {
            println("Your index position is wrong.")
            return
        }
        var h = head
        var curr = 0
        while (curr < index) {
            h = h?.next
            curr++
        }
        val hPrev = h?.prev
        val hNext = h?.next
        hPrev?.next = hNext
        hNext?.prev = hPrev
        length--
        println("Delete of an element at $index position is successful. ")
    }

    fun deleteOfHead() {
        val h = head
        if (h == null) {
            println("There is no element exist.")
            return
        }
        head = h?.next
        head?.prev = null
        if (h.next == null) {
            tail = head
        }
        println("Delete of an element at head is successful. ")
    }

    fun deleteOfTail() {
        val t = tail
        if (t == null) {
            println("There is no element exist.")
            return
        }
        tail = t?.prev
        tail?.next = null
        if (t.prev == null) {
            head = tail
        }
        println("Delete of an element at tail is successful. ")
    }

    fun traverseForwardList() {
        list.clear()
        var h = this.head
        while (h != null) {
            list.add(h.element!!)
            h = h.next
        }
    }

    fun traverseBackwardList() {
        list.clear()
        var t = this.tail
        while (t != null) {
            list.add(t.element!!)
            t = t.prev
        }
    }
}
