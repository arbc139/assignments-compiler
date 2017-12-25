//Function to compute the Fibonacci numbers from 0 to 20:
//http://en.wikipedia.org/wiki/Fibonacci_number
//
//Result:
//0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765

int fibonacci (int n) {
  int ret;
  if (n == 0) 
    ret = 0;
  else if (n == 1)
    ret = 1;
  else
    ret = fibonacci (n-1) + fibonacci (n-2);
  return ret;
}

int main() {
  int n;
  for (n = 0; n <= 20; n = n + 1) {
    putString("Fibonacci number ");
    putInt(n);
    putString(": ");
    putInt(fibonacci(n));
    putLn();
  }
}
