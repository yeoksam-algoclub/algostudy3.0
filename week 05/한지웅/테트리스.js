// const input = require('fs').readFileSync('/dev/stdin').toString().split(' ');
let input = require('fs').readFileSync('./input.txt').toString().trim().split('\n');
let caseNum = 0;
while (input.length > 0) {
    const n = Number(input.splice(0, 1)[0]);
    if (n === 0) break;
    
    caseNum++;
    
    const matrix = input.splice(0, n).map((row) => {
        return row.trim().split(/\s+/).filter(x => x !== "").map(Number);
    });

    // 배열 [dr, dc] 형태로!
    const SHAPES = [
        // I (2)
        [[0,0], [0,1], [0,2], [0,3]],
        [[0,0], [1,0], [2,0], [3,0]],
        // O (1)
        [[0,0], [0,1], [1,0], [1,1]],
        // T (4)
        [[0,0], [0,1], [0,2], [1,1]],
        [[0,0], [1,0], [2,0], [1,1]],
        [[1,0], [1,1], [1,2], [0,1]],
        [[0,1], [1,0], [1,1], [2,1]],
        // S (2)
        [[0,1], [0,2], [1,0], [1,1]],
        [[0,0], [1,0], [1,1], [2,1]],
        // Z (2)
        [[0,0], [0,1], [1,1], [1,2]],
        [[0,1], [1,0], [1,1], [2,0]],
        // L (4)
        [[0,0], [1,0], [2,0], [2,1]],
        [[0,0], [0,1], [0,2], [1,0]],
        [[0,0], [0,1], [1,1], [2,1]],
        [[0,2], [1,0], [1,1], [1,2]],
        // J (4)
        [[0,0], [0,1], [1,0], [2,0]],
        [[0,0], [1,0], [1,1], [1,2]],
        [[0,1], [1,1], [2,0], [2,1]],
        [[0,0], [0,1], [0,2], [1,2]],
    ];

    let maxSum = -Infinity;

    for (let r = 0; r < n; r++) {
        for (let c = 0; c < n; c++) {
            for (const shape of SHAPES) {
                let total = 0;
                let valid = true;

                for (const [dr, dc] of shape) {
                    const nr = r + dr;
                    const nc = c + dc;

                    if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                        total += matrix[nr][nc];
                    } else {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    maxSum = Math.max(maxSum, total);
                }
            }
        }
    }

    console.log(`${caseNum}. ${maxSum}`);
}