# Gaia

A simple stupid programming language.

## Features
- Simple syntax
- Statically typed
- Compiled to "efficient" bytecode
- Cross-platform ish?

## Types
- string
- number(int, float)
- boolean

### Type Groups
Type groups are used to define a set of types that can be used interchangeably in certain contexts, such as function return types or variable declarations.
This is mainly intended for error handling, but you are free to use it however you like.
However you have to use the `oneOf` keyword to indicate that you are using a type group.

```
group GroupName [
    Type1,
    Type2,
    Type3
]
```
## Functions

```
(private || public)? func type functionName(type param1, type param2) {
    // function body
    return value
}
```

## Variables

Types of variables will be inferred upon first assign. Before that they will be an undefined any.
Types of variables may **not** change on reassign.
Attempting to reassing a type will result in a TypeChangeException.

```
(private || public)? let variableName = value
```

constants may be declared with `const` keyword.

```
(private || public)? const CONSTANT_NAME = value
```

### Arrays
Arrays may be created using the Array class.
Similar to variables arrays will inherit the type that is first assigned and can not be changed.
Attempting to do so will result in a TypeChangeException

```
(private || public)? let array = new Array()
```

```
(private || public)? let array = new Array(4, 3, 6) // => [4, 3, 6]
```

```
(private || public)? let array = new Array(4, 2, "7") // => throws TypeChangeException
```

#### Array Methods

- push(value): adds value to the end of the array
- pop(): removes and returns the last value of the array
- get(index): returns the value at the specified index
- set(index, value): sets the value at the specified index
- length(): returns the length of the array
- clear(): removes all elements from the array
- indexOf(value): returns the index of the first occurrence of value, or -1 if not found
- contains(value): returns true if the array contains the specified value, false otherwise
- forEach(func): executes the provided function once for each array element

## Control Flow

```
if (condition) {
    // code
} else if (condition) {
    // code
} else {
    // code
}
```

```
while (condition) {
    // code
}
```

```
for (let var = start; var < end; var = var + step) {
    // code
}
```

## Classes

```
(private || public)? class ClassName {
    type property1
    type property2

    constructor(type param1, type param2) {
        this.property1 = param1
        this.property2 = param2
    }

    func type methodName(type param1) {
        // method body
        return value
    }
}

let myInstance = ClassName(arg1, arg2) // new is overrated anyway
```

Interfaces can be used in class declarations to enforce structure.

```
(private || public)? interface InterfaceName {
    type property1
    type property2

    func type methodName(type param1)
}
```

Callables are used to represent functions that can be passed around as first-class citizens.

```
(private || public)? callable InterfaceName (type property1, type property2)
```

```
let myArray = new Array(2, 4, 6)
myArray.forEach((number value) > {
    print(value)
})
```

## Exceptions

Yea I dunno. Errors are always a controversial topic.

Im just going to use errors as values that can be returned from functions.
To make this a little easier Errors will just be classes that all HAVE TO inherit from the base Error class.

```
func Result<int,Error> parseInt(string s) {
    if (!isDigits(s)) { fail Error("invalid") } // fail is used to indicate an error return
    return parsed
}
```

I am very aware that errors as values can lead to function signatures being littered with error types.
In an attempt to mitigate this you can use a type-group as a return type.

```
group MyErrors [
    ErrorType1,
    ErrorType2,
    ErrorType3
]

func Result<int, oneOf MyErrors> parseInt(string s) {
    if (!isDigits(s)) { fail ErrorType1("invalid") }
    return parsed
}
```

Should errors be unrecoverable you may `panic()` which will immediately terminate the program.
You may also use `explodeNow()` because I think thats funny.

## Modules

```
import "path/to/script"
import "path/to/script" as aliasName
```
