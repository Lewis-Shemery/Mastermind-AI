# Mastermind-AI

This is my recreation of the classic boardgame "Mastermind". In this version, the user generates a code and the AI sets out to guess it using Donald Knuth's "Dancing Links" algorithm.

I first generate an array list of string arrays of all possible guesses from 1111-6666. The user is then prompted to enter their secret code which is stored in a string array called guess. I then used Donald Knuth's algorithm to compare each index to every other index which finds the guess that will remove the most amount of guesses. This will always be 1122 for the first guess. 1122 represents the  lowest numerical value that will leave the minimum guesses remaining; 256 after the first guess. The all_guesses array is then modify and the count_max function is ran again. Mathematically, the answer will always be guessed in 5 turns or less.
