#include "umem.h"
#include <sys/mman.h>
#include <stdio.h>

#define PAGE_SIZE 4096  // You might need to adjust this based on your system

// Forward declarations
void removeBlockFromFreeList(struct BlockHeader *blockToRemove);
void coalesceFreeBlocks();

// Allocation strategies
void *bestFit(size_t size);
void *worstFit(size_t size);
void *firstFit(size_t size);
void *nextFit(size_t size);
// Define your block header structure
struct BlockHeader 
{
    size_t size;
    struct BlockHeader *next;
};

// Global variables
static void *memoryRegion = NULL;
static int allocationAlgorithm = BEST_FIT;
static struct BlockHeader *freeList = NULL;

int umeminit(size_t sizeOfRegion, int allocationAlgo) 
{
    if (memoryRegion != NULL || sizeOfRegion <= 0) 
    {
        return -1;  // umeminit should only be called once, and size must be positive
    }

    // Round up size to be a multiple of PAGE_SIZE
    size_t roundedSize = (sizeOfRegion + PAGE_SIZE - 1) / PAGE_SIZE * PAGE_SIZE;

    // Allocate memory from the OS using mmap
    memoryRegion = mmap(NULL, roundedSize, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (memoryRegion == MAP_FAILED) 
    {
        perror("mmap");
        return -1;  // Failed to allocate memory
    }

    // Initialize the free list with a single block representing the entire region
    freeList = (struct BlockHeader *)memoryRegion;
    freeList->size = roundedSize - sizeof(struct BlockHeader);
    freeList->next = NULL;

    allocationAlgorithm = allocationAlgo;

    return 0;  // Success
}

void *umalloc(size_t size) 
{
    size_t alignedSize = (size + 7) / 8 * 8;  // Ensure 8-byte alignment
    switch (allocationAlgorithm) 
    {
        case BEST_FIT:
            // Placeholder: Implement BEST_FIT strategy
            break;
        // Add cases for other allocation algorithms if needed
        default:
            return NULL;  // Invalid allocation algorithm
    }

    return NULL;  // Placeholder: Return NULL if allocation fails
}


void *bestFit(size_t size) 
{
    struct BlockHeader *currentBlock = freeList;
    struct BlockHeader *bestFitBlock = NULL;
    while (currentBlock != NULL) 
    {
        if (currentBlock->size >= size && (bestFitBlock == NULL || currentBlock->size < bestFitBlock->size)) 
        {
            bestFitBlock = currentBlock;
        }
        currentBlock = currentBlock->next;
    }

    if (bestFitBlock != NULL) 
    {
        // Allocate from the best fit block
        size_t remainingSize = bestFitBlock->size - size;
        if (remainingSize > sizeof(struct BlockHeader)) 
        {
            // Split the block if there is enough space for a new block header
            struct BlockHeader *newBlock = (struct BlockHeader *)((char *)bestFitBlock + size + sizeof(struct BlockHeader));
            newBlock->size = remainingSize - sizeof(struct BlockHeader);
            newBlock->next = bestFitBlock->next;
            bestFitBlock->size = size;
            bestFitBlock->next = newBlock;
        }

        // Remove the allocated block from the free list
        removeBlockFromFreeList(bestFitBlock);

        // Return the pointer to the allocated memory
        return (void *)(bestFitBlock + 1);  // Skip the block header
    }
    return NULL;  // No suitable block found
}

// Define helper function to remove a block from the free list
void removeBlockFromFreeList(struct BlockHeader *blockToRemove)
{
    if (blockToRemove == freeList) 
    {
        freeList = blockToRemove->next;
    } else {
        struct BlockHeader *currentBlock = freeList;
        while (currentBlock != NULL && currentBlock->next != blockToRemove) 
        {
            currentBlock = currentBlock->next;
        }
        if (currentBlock != NULL) 
        {
            currentBlock->next = blockToRemove->next;
        }
    }
}

void *worstFit(size_t size) 
{
    struct BlockHeader *currentBlock = freeList;
    struct BlockHeader *worstFitBlock = NULL;
    while (currentBlock != NULL) 
    {
        if (currentBlock->size >= size && (worstFitBlock == NULL || currentBlock->size > worstFitBlock->size)) 
        {
            worstFitBlock = currentBlock;
        }
        currentBlock = currentBlock->next;
    }

    if (worstFitBlock != NULL) 
    {
        // Allocate from the worst fit block
        size_t remainingSize = worstFitBlock->size - size;
        if (remainingSize > sizeof(struct BlockHeader)) 
        {
            // Split the block if there is enough space for a new block header
            struct BlockHeader *newBlock = (struct BlockHeader *)((char *)worstFitBlock + size + sizeof(struct BlockHeader));
            newBlock->size = remainingSize - sizeof(struct BlockHeader);
            newBlock->next = worstFitBlock->next;
            worstFitBlock->size = size;
            worstFitBlock->next = newBlock;
        }

        // Remove the allocated block from the free list
        removeBlockFromFreeList(worstFitBlock);

        // Return the pointer to the allocated memory
        return (void *)(worstFitBlock + 1);  // Skip the block header
    }
    return NULL;  // No suitable block found
}

void *firstFit(size_t size) 
{
    struct BlockHeader *currentBlock = freeList;
    while (currentBlock != NULL) 
    {
        if (currentBlock->size >= size) 
        {
            // Allocate from the first fit block
            size_t remainingSize = currentBlock->size - size;
            if (remainingSize > sizeof(struct BlockHeader)) 
            {
                // Split the block if there is enough space for a new block header
                struct BlockHeader *newBlock = (struct BlockHeader *)((char *)currentBlock + size + sizeof(struct BlockHeader));
                newBlock->size = remainingSize - sizeof(struct BlockHeader);
                newBlock->next = currentBlock->next;
                currentBlock->size = size;
                currentBlock->next = newBlock;
            }

            // Remove the allocated block from the free list
            removeBlockFromFreeList(currentBlock);
            // Return the pointer to the allocated memory
            return (void *)(currentBlock + 1);  // Skip the block header
        }

        currentBlock = currentBlock->next;
    }
    return NULL;  // No suitable block found
}

void *nextFit(size_t size) 
{
    static struct BlockHeader *lastAllocatedBlock = NULL;
    struct BlockHeader *currentBlock;
    if (lastAllocatedBlock == NULL) 
    {
        currentBlock = freeList;
    } else {
        currentBlock = lastAllocatedBlock->next;
    }

    while (currentBlock != NULL) 
    {
        if (currentBlock->size >= size) 
        {
            // Allocate from the next fit block
            size_t remainingSize = currentBlock->size - size;
            if (remainingSize > sizeof(struct BlockHeader)) 
            {
                // Split the block if there is enough space for a new block header
                struct BlockHeader *newBlock = (struct BlockHeader *)((char *)currentBlock + size + sizeof(struct BlockHeader));
                newBlock->size = remainingSize - sizeof(struct BlockHeader);
                newBlock->next = currentBlock->next;
                currentBlock->size = size;
                currentBlock->next = newBlock;
            }

            // Remove the allocated block from the free list
            removeBlockFromFreeList(currentBlock);
            // Update lastAllocatedBlock for the next fit
            lastAllocatedBlock = currentBlock;
            // Return the pointer to the allocated memory
            return (void *)(currentBlock + 1);  // Skip the block header
        }
        currentBlock = currentBlock->next;
    }
    return NULL;  // No suitable block found
}

int ufree(void *ptr) 
{
    if (ptr == NULL) 
    {
        return 0;  // No operation for NULL pointer
    }
    // Get the block header from the pointer
    struct BlockHeader *blockHeader = (struct BlockHeader *)ptr - 1;
    // Add the block back to the free list
    blockHeader->next = freeList;
    freeList = blockHeader;
    // Perform coalescing
    coalesceFreeBlocks();
    return 0;  // Success
}

// Define helper function to coalesce free blocks
void coalesceFreeBlocks() 
{
    struct BlockHeader *currentBlock = freeList;
    while (currentBlock != NULL && currentBlock->next != NULL) 
    {
        // Check if the current block and its next block are contiguous in memory
        if ((char *)currentBlock + currentBlock->size + sizeof(struct BlockHeader) == (char *)currentBlock->next) 
        {
            // Coalesce the blocks into one bigger free block
            currentBlock->size += currentBlock->next->size + sizeof(struct BlockHeader);
            currentBlock->next = currentBlock->next->next;
        } else {
            currentBlock = currentBlock->next;
        }
    }
}

void umemdump() 
{
    struct BlockHeader *currentBlock = freeList;
    printf("Free Memory Dump:\n");
    while (currentBlock != NULL) 
    {
        printf("Block: %p, Size: %zu\n", (void *)currentBlock, currentBlock->size);
        currentBlock = currentBlock->next;
    }
}

