

from mahjong_aux import Player

from collections import deque


def win_message(hand, num_moves):
    print('WIN!!!! :D')
    print('Number of hands: ' + str(num_moves))
    print('Winning hand: ' + str(' '.join([str(h) for h in hand])))


def play(file_name):

    num_moves = 0
    # read in the tile sequence
    with open(file_name, 'r') as f:
        lines = f.readlines()
        deal_sequence = deque([])
        for line in lines:
            tile = line.strip('\n')
            deal_sequence.append(tile)

    # initializations
    player = Player()

    # first deal
    for i in range(13):
        player.add(deal_sequence.popleft())

    # main game
    while len(deal_sequence) > 0:
        check = player.add(deal_sequence.popleft())
        if check is 2:
            win_message(player.hand, num_moves)
            break
        check = player.remove()
        if check is 2:
            win_message(player.hand, num_moves)
            break
        num_moves = num_moves + 1


# play the game; replace dealsequence.csv with desired deal sequence file
play('supplementtilesequence.csv')

