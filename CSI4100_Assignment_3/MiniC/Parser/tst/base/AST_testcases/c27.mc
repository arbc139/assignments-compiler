// dangling else problem:
void main() {  
  if (x <= 2)
    if (x >= a)
      y = 1;
    else
      y = 2;
}
