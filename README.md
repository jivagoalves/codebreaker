# codebreaker

Implementation of the Code Breaker game in Clojure presented in [doc/GFOS.pdf](doc/GFOS.pdf).

## Installation

You'll need JDK 8 and [Leiningen](https://leiningen.org).

## Usage

Build and run it:
```
$ lein uberjar
$ bin/codebreaker
```

## Examples

```
$ bin/codebreaker
Welcome!
> RRRRR
O
> BBBBB
> GGGGG
O
> RGYYY
XXX
> YRGYY
XXX
> YYRGY
OXX
> YYGRY
OOX
> MWGRY
OXX
You lost!
Secret was PYORG!
```


## License

Copyright © 2019 Jivago Alves

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
