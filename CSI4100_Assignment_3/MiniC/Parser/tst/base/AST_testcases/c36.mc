//find the maximum from 100 integers input by the user:
//ReadInt() is assumed to read 1 integer from the terminal.
void main()
{
  int i, tmp;
  int max = ReadInt();

  for (i=1; i<100; i=i+1) {
    tmp = ReadInt();
    if (tmp > max)
       max = tmp;
  }
}
