bool f() {
   putString("Error: && shortcircuit evaluation not done!\n");
   return false;
}

bool ff() {
   putString("Error: || shortcircuit evaluation not done!\n");
   return false;
}

int main()
{
  if (false && f()){}

  if (true || ff()){}

}

