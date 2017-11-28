//Function to compute the factorial numbers:

int factorial(int n) {
  if (n == 0) 
    return 1;
  else
    return n * factorial(n-1);
}

int main() {
  putInt(factorial(5) );
  putLn();
}

