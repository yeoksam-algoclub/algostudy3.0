#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int main() {
    int n;
    cin >> n;
    vector<long long> arr(n);
    for(int i=0; i<n; i++){
        cin >> arr[i];
    }
    int l =0,r =n-1;
    int ans[2]={l,r};
    long long max_val=max(abs(arr[0]*2),abs(arr[n-1]*2));

    while(l<r){
        long long temp = arr[l]+arr[r];
        if(abs(temp) < abs(max_val)){
            max_val = temp;
            ans[0]=l;
            ans[1]=r;
        }
        
        if(temp<0) l++;
        else if(temp == 0) break;
        else r--;
    }
    
    cout<<arr[ans[0]]<<" "<<arr[ans[1]]<<endl;
    return 0;
}