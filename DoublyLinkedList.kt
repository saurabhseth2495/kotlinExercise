fun main() {
    var notExit: Boolean = true
    var doublyLinkedList: DoublyLinkedList = DoublyLinkedList()
    while (notExit) {
        println()
        println()
        println("....Enter your choice....")
        println(" 1. for add element at head position.")
        println(" 2. for add element at any index position.")
        println(" 3. for show forward list")
        println(" 4. for show backward list")
        println(" 5. for delete object at any index")
        println(" 0. for exit")
        print("Please enter your choice  : ")
        var choice: Int = 0
        try {
            choice = Integer.valueOf(readLine())
        } catch (e: Exception) {
            println("wrong input.")
        }
        when (choice) {
            1 -> addObjectAtHeadPosition(doublyLinkedList)
            2 -> addObjectAtIndexPosition(doublyLinkedList)
            3 -> showForwardList(doublyLinkedList)
            4 -> showBackwardList(doublyLinkedList)
            5 -> deleteObjectAtIndex(doublyLinkedList)
            0 -> notExit = false
        }
    }
}

fun deleteObjectAtIndex(doublyLinkedList: DoublyLinkedList) {
    print("please provide position which you want to delete.")
    var index: Int = 0
    try {
        index = Integer.valueOf(readLine())
    } catch (e: Exception) {
        println("wrong input.")
    }
    doublyLinkedList.deleteAtIndex(index)
}

fun showBackwardList(doublyLinkedList: DoublyLinkedList) {
    doublyLinkedList.traverseBackwardList()
    if (doublyLinkedList.list.size == 0) {
        println("list has no element.")
    } else {
        println("list has some element." + doublyLinkedList.list.size)
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

fun showForwardList(doublyLinkedList: DoublyLinkedList) {
    doublyLinkedList.traverseForwardList()
    if (doublyLinkedList.list.size == 0) {
        println("list has no element.")
    } else {
        println("list has some element." + doublyLinkedList.list.size)
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

fun addObjectAtIndexPosition(doublyLinkedList: DoublyLinkedList) {
    print("please enter position at which you want to add object : ")
    var index: Int = 0
    try {
        index = Integer.valueOf(readLine())
    } catch (e: Exception) {
        println("wrong input.")
    }
    doublyLinkedList.addAtIndex(getValueForAnObject(), index)
}

fun addObjectAtHeadPosition(doublyLinkedList: DoublyLinkedList) {
    println("you pressed one.")
    doublyLinkedList.addAtHead(getValueForAnObject())
}

fun getValueForAnObject(): Student {
    print("Please enter name of student = ")
    var name: String = ""
    try {
        name = readLine().toString()
    } catch (e: Exception) {
        println("wrong input.")
    }
    print("Please enter roll no. of student = ")
    var rollNo: Int = 0
    try {
        rollNo = Integer.valueOf(readLine())
    } catch (e: Exception) {
        println("wrong input.")
    }
    return Student(name, rollNo)
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
        val h = this.head
        val newNode = Node(element)
        newNode.next = this.head
        head = newNode
        if (h == null) tail = newNode else h.prev = newNode
        this.length++
    }

    fun addAtTail(element: Any?) {
        var h = this.head
        val newNode = Node(element)
        newNode.next = null
        while (h?.next != null) {
            h = h.next
        }
        h?.next = newNode
        newNode.prev = h
        tail = newNode
        this.length++
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
    }

    fun getLength(): Int = this.length

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
