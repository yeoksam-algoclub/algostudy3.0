N, M = gets.split.map(&:to_i) # n, m

# empty array & input
arr = []
N.times do
  arr << gets.to_i
end

sorted_arr = arr.sort!

minimum = 0
maximum = sorted_arr[arr.length - 1] * M

answer = 0

while minimum <= maximum do
  mid = (minimum + maximum) / 2 # 정수끼리의 연산 => ruby는 floor 확정
  
  # 해당 시간 (mid)동안 각 심사대가 몇명을 심사할 수 있는지 계산 (해당 시간동안 총 심사 가능 인원)
  count = 0
  sorted_arr.each do |time|
    count += mid / time # 한 심사대 당 몇명 심사 가능
  end

  # 만약, 심사가능 인원이 주어진 인원보다 많다면 (시간을 더 줄일 수 있음)
  if count >= M
    maximum = mid - 1
    answer = mid # 만약 더 줄였는데, 불가능한 상황이라면 해당 값이 정답으로 출력되어야함.
  else
    minimum = mid + 1
  end
end

puts(answer)