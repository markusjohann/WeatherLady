package Generics;

public class Main {
    public static void main(String[] args) {
        Pair<String , Integer> pair = new Pair("JavaAdvanced", 10);
        System.out.println(pair);
    }


}

class Pair<A, B> {
    private A word;
    private B value;


    public Pair(A word, B value) {
        this.word = word;
        this.value = value;
    }

    public A getWord(){
        return word;
    }

   public B getValue(){
        return value;
   }

   public void setWord(){
        this.word = word;
   }

   public void setValue(){
        this.value = value;
   }

    @Override
    public String toString() {
        return "Pair{" +
                "word=" + word +
                ", value=" + value +
                '}';
    }
}

