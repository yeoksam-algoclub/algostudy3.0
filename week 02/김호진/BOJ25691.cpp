#include <bits/stdc++.h>
using namespace std;

static int K;
vector<pair<int, vector<int>>> tree;
vector<int> apple;

int bfs(vector<int> &ind) {
    queue<int> q;
    q.push(0);

    int ss = 0, cnt = 0;
    while (!q.empty()) {
        int t = q.front();
        q.pop();
        if (apple[t] == 1) ++ss;
        ++cnt;

        for (int i : tree[t].second) {
            if (ind[i] == 1) {
                q.push(i);
            }
        }
    }

    if (cnt == K) return ss;
    else return 0;
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N >> K;

    tree = vector<pair<int, vector<int>>>(N);

    for (int i=0; i<N-1; ++i) {
        int p, c;
        cin >> p >> c;

        tree[c].first = p;
        tree[p].second.push_back(c);
    }
    
    apple = vector<int>(N);

    for (int i=0; i<N; ++i) {
        cin >> apple[i];
    }

    vector<int> ind(N, 1);
    for (int i=0; i<N-K; ++i) ind[i] = 0;

    int ans = 0;
    do {
        ans = max(ans, bfs(ind));
    } while (next_permutation(ind.begin(), ind.end()));

    cout << ans;

    return 0;
}