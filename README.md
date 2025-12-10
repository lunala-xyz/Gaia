# Gaia

The culmination of what I've learned from Lynx, Lunala (Lanugage) and Atlas.<br>
None of which. Ever saw the light of day.<br>
(Except for Lynx, that has a great and def finished compiler)

## But does it actually work this time?
In short: Yes<br>
In long: Uhh probably?<br>
It might break I'm not all too sure<br>
I haven't gotten it to break break yet, atleast nothing I could not fix.<br>
Besides that I'm sure the spec isn't fully working yet? Idk I don't remember what I've done and what not.

## This is Gaia
`fibonacci.gaia`
```
const n = 100;
let a = 0;
let b = 1;

print("Fibbonacci sequence for " + n + " times:");

for (let i = 0; i < n; i += 1)  {
    print(a);
    let temp = a;
    a = b;
    b = temp + b;
}
```

Gaia sports a somewhat sleek syntax with all of your basic features.
It's somewhat statically typed. Sometimes it goes a bit JS.<br>
I will fix that...<br>
<br>
I mean eventually<br>

## The language spec

If you really want to read that cracked up spec. I won't stop you.<br>
You can find it [here](https://github.com/lunala-xyz/Gaia/blob/master/gaia_spec.md)
