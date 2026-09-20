public class QuickSorter {
    void sort(int[] arr){
        int lebght = arr.length;
        if (lebght <= 1) return;

        int middle = lebght/2;
        int[] leftArr = new int[middle];
        int[] rightArr = new int[lebght-middle];

        int i = 0;
        int j = 0;

        for (; i <lebght; i++){
            if(i<middle){
                leftArr[i] = arr[i];
            }
            else{
                rightArr[j] = arr[i];
                j++;
            }
        }
        sort(leftArr);
        sort(rightArr);
        merge(leftArr, rightArr, arr);
    }

    void merge(int[] leftArr, int[] rightArr, int[] arr){
        int leftSize = arr.length/2;
        int rightSize = arr.length-leftSize;
        int i = 0, l =0, r =0;

        while(l<leftSize && r<rightSize){
            if(leftArr[l] < rightArr[r]){
                arr[i] = leftArr[l];
                i++;
                l++;
            }
            else{
                arr[i] = rightArr[r];
                i++;
                r++;
            }
        }
    }
}
