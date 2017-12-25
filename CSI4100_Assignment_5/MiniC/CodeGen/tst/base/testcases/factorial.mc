//Function to compute the factorial numbers:

int factorial(int n) {
  int ret;
  if (n == 0) 
    ret = 1;
  else
    ret =  n * factorial(n-1);
  return ret;
}

int main() {
  putInt(factorial(5));
  putLn();
}
