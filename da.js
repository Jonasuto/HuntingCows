(use 'clojure.test)

;----------------------------------------------------------
; Activity: Problem Set: Higher-Order Functions
; Date: September 7, 2017.
; Authors:
;          A01373569 Hector Jonas Campos Angeles
;          A01373890 Gabriela Aguilar Lugo
;----------------------------------------------------------


(defn aprox=
  "Checks if x is approximately equal to y. Returns true
  if |x - y| < epsilon, or false otherwise."
  [epsilon x y]
  (< (Math/abs (- x y)) epsilon))


(defn my-map-indexed
  "The function my-map-indexed takes two arguments: a function f and a list lst. It returns a list consisting of the result of applying f to 0
  and the first item of lst, followed by applying f to 1 and the second item in lst, and so on until lst is exhausted. Function f should accept
  2 arguments: index and item. Do not use the predefined map-indexed function."
  [f lst]
  (loop [lst2 lst
         final ()
         cont  0]
    (if (empty?  lst2) (reverse final) (recur  (rest lst2) (cons  (f cont (first lst2))final)(inc cont)))))



(defn my-drop-while
  " The function my-drop-while takes two arguments: a function f and a list lst. It returns a list of items from lst dropping the initial items that
  evaluate to true when passed to f. Once a false value is encountered, the rest of the list is returned. "
  [f lst]
  (loop [result () lst2 lst]
    (if (empty? lst2)
      result
      (if (f (first lst2)) (recur () (rest lst2)) (concat result lst2)))))


(defn bisection
  "It takes a, b, and f as arguments. It finds the corresponding root using the bisection method. The algorithm must stop when a value
  of c is found such that: |f(c)| < 1.0×10-15."
  [a b f]
  (loop [a a
         b b
         c (/ (* (+ a b) 1.0) 2)]
    (if (< (Math/abs (f c)) 1.0E-15)
      c
      (if (<(* (f a) (f c)) 0) (recur a c (/ (* (+ a b) 1.0) 2)) (recur c b (/ (* (+ a b) 1.0) 2))))))


(defn deriv
  "It takes f and h as its arguments, and returns a new function that takes x as argument, and which represents the derivate of
  f given a certain value for h."
  [f h]
  (fn [x] (/(- (f (+ x h)) (f x)) h)))


(defn integral
  "Write the function integral, that takes as arguments a, b, n, and f. It returns the value of the integral, using Simpson's rule."
  [a b n f]
  (loop [cont 0
         numAprox 0]
    (let [ h (/(- b a)n)
          par 2
          impart  4]
      (if (= cont n)
        (/(*( + numAprox (f (+ a (* cont h))))h)3)
        (if(= 0 cont)
          (recur (inc cont) (+ numAprox (f (+ a (* cont h)))))
          (if(even? cont)
            (recur (inc cont)  (+ numAprox (* (f (+ a (* cont h))) par)))
            (recur (inc cont)  (+ numAprox (* (f (+ a (* cont h))) impart)))))))))


; Pruebas

(deftest test-my-map-indexed
  (is (= () (my-map-indexed vector ())))
  (is (= '([0 a] [1 b] [2 c] [3 d])
         (my-map-indexed vector '(a b c d))))
  (is (= '(10 4 -2 8 5 5 13)
         (my-map-indexed + '(10 3 -4 5 1 0 7))))
  (is (= '(0 1 -4 3 1 0 6)
         (my-map-indexed min '(10 3 -4 5 1 0 7)))))

(deftest test-my-drop-while
  (is (= () (my-drop-while neg? ())))
  (is (= '(0 1 2 3 4)
         (my-drop-while
           neg?
           '(-10 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4))))
  (is (= '(2 three 4 five)
         (my-drop-while
           symbol?
           '(zero one 2 three 4 five))))
  (is (= '(0 one 2 three 4 five)
         (my-drop-while
           symbol?
           '(0 one 2 three 4 five)))))


(deftest test-bisection
  (is (aprox= 0.0001
              3.0
              (bisection 1 4 (fn [x] (* (- x 3) (+ x 4))))))
  (is (aprox= 0.0001
              -4.0
              (bisection -5 0 (fn [x] (* (- x 3) (+ x 4))))))
  (is (aprox= 0.0001
              Math/PI
              (bisection 1 4 (fn [x] (Math/sin x)))))
  (is (aprox= 0.0001
              (* 2 Math/PI)
              (bisection 5 10 (fn [x] (Math/sin x)))))
  (is (aprox= 0.0001
              1.618033988749895
              (bisection 1 2 (fn [x] (- (* x x) x 1)))))
  (is (aprox= 0.0001
              -0.6180339887498948
              (bisection -10 1 (fn [x] (- (* x x) x 1))))))


(defn f [x] (* x x x))
(def df (deriv f 0.001))
(def ddf (deriv df 0.001))
(def dddf (deriv ddf 0.001))

(deftest test-deriv
  (is (aprox= 0.05 75 (df 5)))
  (is (aprox= 0.05 30 (ddf 5)))
  (is (aprox= 0.05 6 (dddf 5))))


(deftest test-integral
  (is (= 1/4 (integral 0 1 10 (fn [x] (* x x x)))))
  (is (= 21/4
         (integral 1 2 10
                   (fn [x]
                     (integral 3 4 10
                               (fn [y]
                                 (* x y))))))))


(run-tests)
