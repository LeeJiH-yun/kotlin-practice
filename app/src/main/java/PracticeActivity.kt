fun main() {
    print("Hello")
    var a = 10 //변수 선언
    val b = 20 //상수 선언

    //a = 20 변수는 값을 변경할 수 있고
    //b = 40 상수는 값을 변경할 수 없다 val는 자바의 final 키워드랑 대응

    //메서드와 함수는 혼동되는 용어..
    //메서드는 객체와 관련된 함수이다 함수는 좀 더 일반적인 용어이며 모든 메서드는 함수이다.

    //when문은 switch문에 대응
    when (b) {
        1 -> println("x == 1")
        20,21 -> println("x == 2 or x == 3")
        in 14..18 -> println("14부터 18사이")
        !in 2..3 -> println("2부터 3사이가 아님")
        else -> {
            println("아님")
        }
    }

    for (i in 0..10 step 2) {
        //step 키워드는 증감의 간격 조절
        println(i)
    }
    for (i in 10 downTo 0 step 2) {
        //downTo 키워드는 감소 범위 조절
        //10부터 0까지 2씩 감소하며 출력
        println(i)
    }

    class Person(var name: String) {

    }
}
