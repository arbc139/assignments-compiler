void foo(int a, bool b) {
  putString("foo's arguments: ");
  putInt(a);
  putString(", ");
  putBool(b);
  putLn();
  return;
}

int main()
{
  foo(300, true);
}
