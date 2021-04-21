#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool compareTo()
{
    
}

int buildingBlock(vector<int> &a, vector<int> &b, int n)
{
    vector<pair<int, int>> LIS;
    for (int i = 0; i < n; ++i)
    {
        LIS.push_back({a[i], b[i]});
    }
    sort(LIS.begin(), LIS.end(), compareTo());

    for (int i = 0; i < )
}

int main(int args, char **argv)
{
    vector<int> a1 = {8, 1, 4, 3, 5, 2, 6, 7};
    vector<int> a2 = {1, 2, 3, 4, 5, 6, 7, 8};
    int n = a2.size();
    cout << buildingBlock(a1, a2, n) << endl;
    return 0;
}