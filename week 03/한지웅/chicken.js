// const input = require('fs').readFileSync('/dev/stdin').toString().split(' ');
let input = require('fs').readFileSync('./input.txt').toString().trim().split('\n');

const [n, m] = input.splice(0,1)[0].split(" ");

// 거리 계산 함수
function calculateDistance(r1,r2,c1,c2) {
  const rowDistance = Math.abs(r1-r2)
  const colDistance = Math.abs(c1-c2)
  return rowDistance + colDistance
}

// matrix에서 1: 집, 2: 치킨집
const matrix = input.map((row) => row.split(" ").map(Number));

// 치킨거리는 집과 가장 가까운 치킨집과의 거리
// 각 집에서 각각의 치킨집과의 거리를 측정?
const chickenMap = new Map(); // 나중에 치킨집을 추가하면서  해당 치킨집의 좌표를 미리 저장
const homeMap = new Map(); // 나중에 집을 보면서 집의 좌표를 미리 저장

let homeNumber = 1;
let chickenNumber = 1;

for (let i = 0; i < matrix.length; i++) {
  for (let j = 0; j < matrix[0].length; j++) {
    const current = matrix[i][j]
    if (current === 2) {
      chickenMap.set(chickenNumber, [i,j])
      chickenNumber++
    }
    if (current === 1) {
      homeMap.set(homeNumber, [i,j])
      homeNumber++
    }
  }
}

const distanceMap = new Map();
// 집과 치킨집 거리를 미리 저장
for (const [homeNumber, homeCoord] of homeMap.entries()) {
  const homeRow = homeCoord[0]
  const homeCol = homeCoord[1]
  for (const [chickenNumber, chickenCoord] of chickenMap.entries()) {
    const chickenRow = chickenCoord[0]
    const chickenCol = chickenCoord[1]
    const distance = calculateDistance(homeRow, chickenRow, homeCol, chickenCol)
    const key = `${homeNumber}-${chickenNumber}`
    distanceMap.set(key, distance)
  }
}

// console.log(distanceMap)

const candidates = [];
function combinationChickenZip(current, currentPick) {
  if (currentPick.length == m) {
    // 원하는 만큼 뽑았음.
    candidates.push([...currentPick]);
    return;
  }
  // 범위 초과임.
  if (current > chickenNumber-1) {
    return;
  }

  combinationChickenZip(current+1, [...currentPick])
  combinationChickenZip(current+1, [...currentPick, current])
}

combinationChickenZip(1, [])

let answer = 987654321

// candidates들을 순회하면서, 최소 거리가 되는 것 찾기
for (const candidate of candidates) {
  let sum = 0;
  for (const homeNumber of homeMap.keys()) {
    let minDistance = 987654321;
    for (const chickenNumber of candidate) {
      const key = `${homeNumber}-${chickenNumber}`
      // console.log(key)
      const distance = distanceMap.get(key)
      minDistance = Math.min(minDistance, distance)
    }
    // console.log('min distance', minDistance)
    sum += minDistance
  }
  // console.log(sum)
  answer = Math.min(answer, sum)
}

console.log(answer)