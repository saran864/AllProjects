/*
 * class A{ private final void flip() { System. out.println("Flipper"); } }
 * public class JavaDemo extends A { public static void flip() { System.
 * out.println("Flip"); } public static void main(String[] args) { new
 * JavaDemo().flip(); } }
 */
/*
 * class A{ A() { System. out.println("A"); } } public class JavaDemo extends A
 * { A(){ System.out.println("AA"); } public static void main(String[] args) {
 * new JavaDemo(); } }
 */

/*
 * class A{ private void alpha() { System.out.println("A"); } } public class
 * JavaDemo extends A { void alpha(){ System.out.println("AA"); } public static
 * void main(String[] args) { new JavaDemo().alpha(); } }
 */
/*
 * class A{ int alpha() { return 0; } } public class JavaDemo extends A {
 * protected int alpha(){ return 1; } public static void main(String[] args) {
 * System.out.println(new JavaDemo().alpha()); } }
 */
/*
 * class A{ Number alpha() { return 0; } } public class JavaDemo extends A {
 * public Integer alpha(){ return 1; } public static void main(String[] args) {
 * System.out.println(new JavaDemo().alpha()); } }
 */
/*
 * class A{ static void alpha() {//reimplementation System.out.println("A"); } }
 * public class JavaDemo extends A { static void alpha() {
 * System.out.println("AA"); } public static void main(String[] args) { new
 * JavaDemo().alpha(); } }
 */

/*
 * class A{ int aa=2; } public class JavaDemo extends A { int aa=5; public
 * static void main(String[] args) { A a=new JavaDemo();
 * System.out.println(a.aa); } }
 */