#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pii;

static int N, M, cnt=0, house_size, chicken_size, ans=10000;
vector<pii> house;
vector<pii> chicken;
vector<bool> bit;

void combination(int i) {
    if (cnt == M) {
        int ss = 0;
        
        for (int x=0; x < house_size; ++x) {
            int a = house[x].first, b = house[x].second;
            int dist = 100;
            
            for (int y=0; y < chicken_size; ++y) {
                if (bit[y]) {
                    int na = chicken[y].first, nb = chicken[y].second;
                    dist = min(dist, abs(a-na) + abs(b-nb));
                }
            }

            ss += dist;
        }

        ans = min(ans, ss);
        
        return;
    }

    if (i == chicken_size) return;

    for (int j=i; j < chicken_size; ++j) {
        bit[j] = 1;
        ++cnt;
        combination(j+1);
        
        bit[j] = 0;
        --cnt;
        combination(j+1);
    }
}

int main() {
    cin >> N >> M;

    for (int i=0; i<N; ++i) {
        for (int j=0; j<N; ++j) {
            int a;
            cin >> a;
            if (a == 1) house.push_back(make_pair(i, j));
            else if (a == 2) chicken.push_back(make_pair(i, j));
        }
    }

    house_size = house.size();
    chicken_size = chicken.size();
    bit = vector<bool>(chicken_size, 0);

    combination(0);

    cout << ans;

    return 0;
}