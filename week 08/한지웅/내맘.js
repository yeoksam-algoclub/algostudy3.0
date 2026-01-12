// let input = require('fs').readFileSync('./input.txt').toString().trim().split('\n');
let input = require('fs').readFileSync('/dev/stdin').toString().trim().split(/\s+/).map(Number);
let idx = 0;

const K = input[idx++];
let matrix = [];
for (let i = 0; i < K; i++) {
    matrix.push(input[idx++]);
}
const Q = input[idx++];

const leftINF = -2e9;
const rightINF = 2e9;

// 이진 탐색: p보다 작은 가장 큰 장애물
function findLeft(arr, p) {
    if (arr.length === 0) return leftINF;
    let lo = 0, hi = arr.length - 1, result = leftINF;
    while (lo <= hi) {
        let mid = Math.floor((lo + hi) / 2);
        if (arr[mid] < p) {
            result = arr[mid];
            lo = mid + 1;
        } else {
            hi = mid - 1;
        }
    }
    return result;
}

// 이진 탐색: p보다 큰 가장 작은 장애물
function findRight(arr, p) {
    if (arr.length === 0) return rightINF;
    let lo = 0, hi = arr.length - 1, result = rightINF;
    while (lo <= hi) {
        let mid = Math.floor((lo + hi) / 2);
        if (arr[mid] > p) {
            result = arr[mid];
            hi = mid - 1;
        } else {
            lo = mid + 1;
        }
    }
    return result;
}

function countEven(a, b) {
    if (a > b) return 0;
    return Math.floor(b / 2) - Math.floor((a - 1) / 2);
}

function countOdd(a, b) {
    if (a > b) return 0;
    return (b - a + 1) - countEven(a, b);
}

let results = [];

for (let i = 0; i < Q; i++) {
    const t = input[idx++];
    const p = input[idx++];
    
    const lefthuddle = findLeft(matrix, p);
    const righthuddle = findRight(matrix, p);

    const realMinP = Math.max(p - t, lefthuddle + 1);
    const realMaxP = Math.min(p + t, righthuddle - 1);

    const relativeMinP = realMinP - p;
    const relativeMaxP = realMaxP - p;

    if (t % 2 === 0) {
        results.push(countEven(relativeMinP, relativeMaxP));
    } else {
        results.push(countOdd(relativeMinP, relativeMaxP));
    }
}

console.log(results.join('\n'));