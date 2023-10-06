//This code shows the power of cache and how to scan a matrix by row or by column.

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int** init(int size){ // function for allocating matrix M with a segment of size^2 consecutive words.
    int** M = (int**) malloc(sizeof(int *) * size);
    for(int i = 0; i < size; i++){
        M[i] = (int*) calloc(size,sizeof(int));
        for (int j = 0; j<size; j++){
        M[i][j]=i*size+j;
    }
    }
    return M;
}

int main(int argc, char ** argv){
    int size=atoi(argv[1]);


    int** M = init(size); // build the input matrix M of dimension size x size
    
    //Start by summing by row
    printf("Matrix of integers: number of rows %d, number of columns %d, bytes per integer: %d \n",size, size, sizeof(int));
    printf("Start summing matrix M by row...\n");
    clock_t begin = clock(); //used for measuring execution time
    
    for(int i=0; i<3; i++){
        int sum = 0;
        for(int i=0; i<size; i++){ //row
            for(int j=0; j<size; j++){ //column
                sum = sum + M[i][j];
            }
        }
    }
    
    clock_t end = clock();//used for measuring execution time
      double elapsed_secs_row = (double)(end - begin) / (3*CLOCKS_PER_SEC);//used for measuring execution time

    printf("End sum by row.\n");
    printf("Time sum by row: %f seconds\n", elapsed_secs_row);
    printf("Average time per element: %f microseconds\n\n", 1000000*elapsed_secs_row/(size*size));
    
    

    //Start by summing by row
    printf("Start scanning matrix M by column...\n");
    begin = clock();//used for measuring execution time

    for(int i=0; i<3; i++){
        int sum = 0;
        for(int j=0; j<size; j++){ //column
            for(int i=0; i<size; i++){ //row
                sum = sum + M[i][j];
            }
        }
    }

    end = clock();//used for measuring execution time
      double elapsed_secs_col = (double)(end - begin) / (3*CLOCKS_PER_SEC);//used for measuring execution time
    printf("End sum by column.\n");
    printf("Time sum by column: %f seconds\n", elapsed_secs_col);
    printf("Average time per element: %f microseconds\n\n", 1000000*elapsed_secs_col/(size*size));
 
    free(M);
    return 0;
}
