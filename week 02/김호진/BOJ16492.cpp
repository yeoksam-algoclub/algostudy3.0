#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    vector<pair<char, string>> gens;
    vector<string> dels;
    string target, command;
    
    while (command[0] != 't') {
        cin >> command;
        if (command[0] == 'g') {
            char X;
            string Y;
            cin >> X >> Y >> Y;
            gens.push_back(make_pair(X, Y));
        }
        else if (command[0] == 'd') {
            string X;
            cin >> X;
            dels.push_back(X);
        }
        else cin >> target;
    }

    string s = "A";
    
    for (int i=0; i<10; ++i) {

        // 생성
        string gen_s = "";
        for (int j=0; j < s.size(); ++j) {
            bool flag = 1;
            for (pair<char, string> it: gens) {
                if (s[j] == it.first) {
                    flag = 0;
                    gen_s.append(it.second);
                }
            }
            if (flag) gen_s.push_back(s[j]);
        }
        gen_s;
        s = gen_s;

        // 소멸
        vector<bool> v(s.size(), 0);
        string del_s = "";

        for (string d: dels) {
            int idx = 0;
            while (idx < s.size()-d.size()+1) {
                idx = s.find(d, idx);
                if (idx == -1) break;
                for (int w=0; w < d.size(); ++w) v[idx+w] = 1;
                ++idx;
            }
        }
        
        for (int j=0; j < s.size(); ++j) {
            if (v[j] == 0) del_s.push_back(s[j]);
        }
        s = del_s;
        
        // 판정
        if (s == target) {
            cout << 'O';
            return 0;
        }
    }
    
    cout << 'X';
    
    return 0;
}