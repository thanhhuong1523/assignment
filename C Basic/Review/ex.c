#include <stdio.h>
#include <math.h>

#define MAX 100
int arrLength;
int arr[MAX];

void inputArray() {
    while(1) {
        scanf("%d", &arrLength);
        if(arrLength < 1 || arrLength > 100) {
            printf("Invalid array's length! Please input again!!\n");
        } else {
            break;
        }
    }

    for(int i = 0; i < arrLength; i++) {
        scanf("%d", &arr[i]);
    }
}

void outputArray() {
    for(int i = 0; i < arrLength; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void displayDescArray() {
    int tmp;
    for(int i = 0; i < arrLength - 1; i++) {
        for(int j = i + 1; j < arrLength; j++) {
            if(arr[i] < arr[j]) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
    }

    outputArray();
}

void checkOddArray() {
    int flag = 1;
    for(int i = 0; i < arrLength; i++) {
        if(arr[i] % 2 == 0) {
            flag = 0;
            break;
        }
    }
    if(flag == 1) {
        printf("Odd array!\n");
    } else {
        printf("Not odd array!\n");
    } 
}

void searchValue() {
    int key;
    printf("Input your key: ");
    scanf("%d", &key);
    int cnt = 0;
    for(int i = 0; i < arrLength; i++) {
        if(arr[i] == key) cnt++;
    }
    printf("Number %d appears %d times\n", key, cnt);
}

int checkPrimeNumber(int num) {
    if(num < 2) return 0;
    for(int i = 2; i <= sqrt(num); i++) {
        if(num % i == 0) {
            return 0;
        }
    }
    return 1;
}

void printPrimeNumber() {
    printf("Prime numbers in the array: ");
    int check[MAX] = {0};

    for(int i = 0; i < arrLength; i++) {
        if(checkPrimeNumber(arr[i])) {
            if(check[arr[i]] == 0) {
                printf("%d ", arr[i]);
            }
            check[arr[i]] = 1;
        }
    }
    printf("\n");
}

int quit() {
    int choice = 0;
    printf("Are you sure ? Enter 1 to exit the application! (1/0): ");
    scanf("%d", &choice);
    if(choice == 1) {
        return 1;
    }
    return 0;
}

int main() {
    int choice;

    while(1) {
        printf("------ Arrays ------\n");
        printf("1. Input the array\n");
        printf("2. Output the array\n");
        printf("3. Print out the array in descending order.\n");
        printf("4. Check if all elements of the array are odd.\n");
        printf("5. Search a value\n");
        printf("6. Display prime numbers in the array.\n");
        printf("7. Quit\n");

        printf("Your choice is: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                inputArray();
                break;
            case 2:
                outputArray();
                break;
            case 3:
                displayDescArray();
                break;
            case 4:
                checkOddArray();
                break;
            case 5:
                searchValue();
                break;
            case 6:
                printPrimeNumber();
                break;
            case 7:
                if(quit() == 1) {
                    return 0;
                }
                break;
            default:
                printf("Invalid choice!\n");
        }
    }

    return 0;
}